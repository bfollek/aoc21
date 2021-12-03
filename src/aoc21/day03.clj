(ns aoc21.day03
  (:require
   [rabbithole.core :as rh]))

(defn part-1
  [file-name]
  (let [diagnostics (rh/read-lines file-name)
        ;; Set up counters for each digit, and init them to 0.
        counters  (into (vector-of :int) (repeat (count (nth diagnostics 0)) 0))]
    counters))

(defn part-2
  [file-name])