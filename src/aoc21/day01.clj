(ns aoc21.day01
  (:require
   [rabbithole.core :as rh]))

(defn- load-ints
  [file-name]
  (->>
   file-name
   rh/read-lines
   (map rh/to-int)))

(defn- count-larger
  "Count the elements that are larger than the preceding element."
  [sq]
  (let
    ;; lazy-seq to vector so that we can look at prev elements quickly.
   [v (into [] sq)
    ;; %1 is the index; %2 is the element.
    ;; Keep when the index > 0 and the element > prev element.
    ;; We end up with a seq of indexes that we can count.
    ;; We could as easily use `true` instead of the index, but the 
    ;; actual indexes might be useful for debugging.
    larger (keep-indexed #(when (and (> %1 0) (> %2 (nth v (dec %1)))) %1) v)]
    (count larger)))

(defn part-1
  "How many measurements are larger than the previous measurement?"
  [file-name]
  (->>
   file-name
   load-ints
   count-larger))

(defn part-2
  "Consider sums of a three-measurement sliding window. How many sums are larger than the previous sum?"
  [file-name]
  (->>
   file-name
   load-ints
   ;; `partition` gives us a sliding window:
   ;; aoc21.core=> (partition 3 1 v)
   ;; ((10 12 9) (12 9 13) (9 13 8))
   (partition 3 1)
   (map #(apply + %1))
   count-larger))
