(ns kafka-cloducer.core
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

(defn kafka-producer
  [message]
  (let [kafka-producer (initialize-or-get-producer (merge string-serde-config bootstrap-servers))]
    (.send kafka-producer (ProducerRecord. "test" message))))
