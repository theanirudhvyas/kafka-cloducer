(ns kafka-cloducer.core
  (:require [clojure.tools.logging :as log])
  (:import [org.apache.kafka.common.serialization ByteArraySerializer StringSerializer]
           [org.apache.kafka.clients.producer KafkaProducer ProducerRecord]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def producer-obj (atom nil))

(def string-serde-config
  {"key.serializer" StringSerializer
   "value.serializer" StringSerializer})

(def bootstrap-servers
  {"bootstrap.servers" "localhost:9092"})

(defn initialise-or-get-producer [properties]
  (when (nil? @producer-obj)
    (reset! producer-obj (KafkaProducer. properties)))
  @producer-obj)

(defn send-message
  [message]
  (let [kafka-producer (initialise-or-get-producer (merge string-serde-config bootstrap-servers))]
    (prn "sending message: " message)
    (.send kafka-producer (ProducerRecord. "test" message))))


(defn -main []
  (prn "sending 10 messages to kafka")
  (doseq [message (take 10 (map #(str "test-message-" %) (range)))]
    (send-message message)))
