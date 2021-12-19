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
  [diagnostics use-most-common-bits?]
  (loop [diags diagnostics index 0]
    (comment (println "count diags:" (count diags)))
    (let [[mcb lcb] (common-bits diags)
          bits (if use-most-common-bits? mcb lcb)]
      (when (>= index (count bits))
        (throw (ArrayIndexOutOfBoundsException. "index > bits size:")))
      (if (<= (count diags) 1)
        (bit-string-to-number (first diags))
        (recur (check-bit diags bits index) (inc index))))))

(defn rating-using-reduced
  [diagnostics use-most-common-bits?]
  (reduce (fn [diags index]
            (comment (println "count diags:" (count diags)))
            (if (<= (count diags) 1)
               ;; We may finish before we've checked all the bits.
              (reduced diags)
              (let [[mcb lcb] (common-bits diags)
                    bits (if use-most-common-bits? mcb lcb)]
                (check-bit diags bits index))))
          diagnostics
          (range (count (first diagnostics)))))

(defn part-2
  [file-name]
  (let [diagnostics (rh/read-lines file-name)
        oxy (rating diagnostics true)
        co2 (rating diagnostics false)]
    (* oxy co2)))
    ;; This version is for `rating-using-reduced`.
    ;; `oxy` and `co2` are one-element string seqs. The string
    ;; is binary digits. Extract the string and convert it to a number.
    ;;(apply * (map #(->> %1 first bit-string-to-number) [oxy co2]))))
