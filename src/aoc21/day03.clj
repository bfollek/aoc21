(ns aoc21.day03
  (:require
   [rabbithole.core :as rh]))

(defn count-one-bits
  [counters diagnostic]
  (map #(+ %1 (if (= %2 \1) 1 0)) counters diagnostic))

(defn count-most-common-bits
  [diagnostics]
  (let [counters (into (vector-of :int) (repeat (count (nth diagnostics 0)) 0))
        counters     (reduce count-one-bits counters diagnostics)]
    counters))

(defn part-1
  [file-name]
  (let [diagnostics (rh/read-lines file-name)
        counters (count-most-common-bits diagnostics)]
        ;; Set up counters for each digit, and init them to 0.
        ;; counters  (into (vector-of :int) (repeat (count (nth diagnostics 0)) 0))]
        ;; (->>
        ;; counters
        ;; (count-most-common-bits diagnostics))
    counters))

(defn part-2
  [file-name])