(ns aoc21.day03
  (:require
   [clojure.string :as str]
   [rabbithole.core :as rh]))

(defn one-bits
  "If the nth diagnostic bit is 1, add 1 to the nth counter.
  Return the updated counters vector."
  [counters diagnostic]
  (map #(if (= %2 \1) (+ %1 1) %1) counters diagnostic))

(defn most-common-bits
  [diagnostics]
  ;; https://twitter.com/kelvinmai/status/1466914942318043139
  (->> diagnostics
       (apply map vector) ; Transpose.
       (map #(reduce + 0 %1))
       (map #(if (>= %1 (/ (count diagnostics) 2)) 1 0))))

(defn common-bits
  [diagnostics]
  (let [mcb (most-common-bits diagnostics)
        lcb (map #(Math/abs (- %1 1)) mcb)] ; Least common bits - flip each bit.
    [mcb lcb]))

(defn bit-vector-to-number
  [sq]
  (Integer/parseInt (apply str sq) 2))


(defn check-bit
  [diagnostics bits index]
  (let [bit (nth bits index)]
    (remove #(not= (nth %1 index) bit) diagnostics)))

(defn rating-loop
  [use-most-common-bits? diagnostics]
  (loop [diags diagnostics index 0]
    #_(println "count diags:" (count diags))
    (if (<= (count diags) 1)
      ;; Found the rating.
      (do
        (println "first diags" (first diags))
        (bit-vector-to-number (first diags)))
      ;; Keep looking.
      (let [[mcb lcb] (common-bits diags)
            bits (if use-most-common-bits? mcb lcb)
            bits-size (count bits)]
        ;; Probably not necessary, but make sure we don't loop forever.
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
    (-> r first bit-vector-to-number)))

(defn load-diagnostics
  [file-name]
  ;; split, int here
  (->> file-name
       (rh/read-lines)
       (map #(str/split %1 #""))
       (map #(map rh/to-int %1))))

(defn part-1
  [file-name]
  (let [diagnostics (load-diagnostics file-name)
        [mcb lcb] (common-bits diagnostics)
        gamma-rate (bit-vector-to-number mcb)
        epsilon-rate (bit-vector-to-number lcb)]
    (* gamma-rate epsilon-rate)))

(defn part-2
  [file-name]
  (let [diagnostics (load-diagnostics file-name)
        oxy (rating-loop true diagnostics)
        co2 (rating-loop false diagnostics)]
    (* oxy co2)))

(defn part-2-reduced
  [file-name]
  (let [diagnostics (load-diagnostics file-name)
        oxy (rating-reduced true diagnostics)
        co2 (rating-reduced false diagnostics)]
    (* oxy co2)))
