(defproject kafka-cloducer "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.logging "1.1.0"]
                 [org.apache.kafka/kafka-clients "2.5.0"]
                 ]
  :main   kafka-cloducer.core
  :repl-options {:init-ns kafka-cloducer.core})
