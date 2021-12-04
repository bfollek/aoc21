
(ns aoc21.day03
  (:require
   [rabbithole.core :as rh]))

(defn one-bits
  "If the nth diagnostic bit is 1, add 1 to the nth counter.
  Return the updated counters vector."
  [counters diagnostic]
  (map #(if (= %2 \1) (+ %1 1) %1) counters diagnostic))

(defn most-common-bits
  [diagnostics]
  ;; Start with a vector of zeros, one for each bit in a diagnostic.
  (as-> (into (vector-of :int) (repeat (count (nth diagnostics 0)) 0)) counters
    (reduce one-bits counters diagnostics)
    (map #(if (>= %1 (/ (count diagnostics) 2)) 1 0) counters)))

(defn common-bits
  [diagnostics]
  (let [mcb (most-common-bits diagnostics)
        lcb (map #(Math/abs (- %1 1)) mcb)] ; least common bits - flip each bit.
    [mcb lcb]))

(defn bit-seq-to-decimal
  [sq]
  (Integer/parseInt (apply str sq) 2))

(defn part-1
  [file-name]
  (let [[mcb lcb] (common-bits (rh/read-lines file-name))
        gamma-rate (bit-seq-to-decimal mcb)
        epsilon-rate (bit-seq-to-decimal lcb)]
    (* gamma-rate epsilon-rate)))

(defn check-bit
  [diagnostics bits index]
  (let [bit (nth bits index)]
    (remove #(not (= (Character/digit (nth %1 index) 2) bit)) diagnostics)))

(defn rating
  [diagnostics use-most-common-bits?]
  (loop [d diagnostics index 0]
    (let [[mcb lcb] (common-bits d)
          bits (if use-most-common-bits? mcb lcb)]
      (if (<= (count d) 1)
        (bit-seq-to-decimal (first d))
        (recur (check-bit d bits index) (inc index))))))

(defn part-2
  [file-name]
  (let [diagnostics (rh/read-lines file-name)
        oxy (rating diagnostics true)
        co2 (rating diagnostics false)]
    (* oxy co2)))
