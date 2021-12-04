(ns aoc21.day03
  (:require
   [rabbithole.core :as rh]))

(defn one-bits
  [counters diagnostic]
  (map #(+ %1 (if (= %2 \1) 1 0)) counters diagnostic))

(defn most-common-bits
  [diagnostics]
  (let [counters (into (vector-of :int) (repeat (count (nth diagnostics 0)) 0))
        counters     (reduce one-bits counters diagnostics)]
    (map #(if (> %1 (/ (count diagnostics) 2)) 1 0) counters)))

(defn part-1
  [file-name]
  (let [diagnostics (rh/read-lines file-name)
        mcb (most-common-bits diagnostics)]
        ;; Set up counters for each digit, and init them to 0.
        ;; counters  (into (vector-of :int) (repeat (count (nth diagnostics 0)) 0))]
        ;; (->>
        ;; counters
        ;; (count-most-common-bits diagnostics))
    mcb))

(defn part-2
  [file-name])