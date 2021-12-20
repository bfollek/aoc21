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

(defn bit-string-to-number
  [sq]
  (Integer/parseInt (apply str sq) 2))

(defn part-1
  [file-name]
  (let [[mcb lcb] (common-bits (rh/read-lines file-name))
        gamma-rate (bit-string-to-number mcb)
        epsilon-rate (bit-string-to-number lcb)]
    (* gamma-rate epsilon-rate)))

(defn check-bit
  [diagnostics bits index]
  (let [bit (nth bits index)]
    (remove #(not (= (Character/digit (nth %1 index) 2) bit)) diagnostics)))

(defn rating
  [use-most-common-bits? diagnostics]
  (loop [diags diagnostics index 0]
    #_(println "count diags:" (count diags))
    (if (<= (count diags) 1)
      ;; Found the rating.
      (bit-string-to-number (first diags))
      ;; Keep looking.
      (let [[mcb lcb] (common-bits diags)
            bits (if use-most-common-bits? mcb lcb)
            bits-size (count bits)]
        (when (>= index bits-size)
          (throw (ArrayIndexOutOfBoundsException. (format "index %d > bits size %d" index bits-size))))
        (recur (check-bit diags bits index) (inc index))))))

(defn reducer
  [use-most-common-bits? diagnostics index]
  #_(println "count diags:" (count diags))
  (if (<= (count diagnostics) 1)
    ;; We may finish before we've checked all the bits.
    (reduced diagnostics)
    ;; Because we're removing `diagnostics` from the collection,
    ;; we have to recalc `(common-bits)` each time through. 
    (let [[mcb lcb] (common-bits diagnostics)
          bits (if use-most-common-bits? mcb lcb)]
      (check-bit diagnostics bits index))))

(defn rating-reduced
  [use-most-common-bits? diagnostics]
  (let [r (reduce (partial reducer use-most-common-bits?)
                  diagnostics
                  (range (count (first diagnostics))))]
    (-> r first bit-string-to-number)))

(defn part-2
  [file-name]
  (let [diagnostics (rh/read-lines file-name)
        oxy (rating true diagnostics)
        co2 (rating false diagnostics)]
    (* oxy co2)))

(defn part-2-reduced
  [file-name]
  (let [diagnostics (rh/read-lines file-name)
        oxy (rating-reduced true diagnostics)
        co2 (rating-reduced false diagnostics)]
    (* oxy co2)))
