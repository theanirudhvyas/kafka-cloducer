(ns kafka-cloducer.core
  (:require [clojure.tools.logging :as log])
  (:import [org.apache.kafka.common.serialization ByteArraySerializer StringSerializer]
           [org.apache.kafka.clients.producer KafkaProducer ProducerRecord]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def producer-obj (promise))

(def string-serde-config
  {"key.serializer" StringSerializer
   "value.serializer" StringSerializer})

(def bootstrap-servers
  {"bootstrap.servers" "localhost:9092"})

(defn initialise-producer [properties]
  (deliver producer-obj (KafkaProducer. properties)))

(defn send-message
  [message]
    (.send @producer-obj (ProducerRecord. "test" "key" message)))

(defn -main []
  (log/info "Initialising Kafka Producer")
  (initialise-producer (merge string-serde-config bootstrap-servers))
  (prn "sending 10 messages to kafka")
  (doseq [message (take 10 (map #(str "test-message-" %) (range)))]
    (send-message message))
  ;; Adding a sleep for the producer to finish sending the messages (it returns a future insatance).
  (Thread/sleep 20))
