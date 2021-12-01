(ns aoc21.day01
  (:require
   [rabbithole.core :as rh]))

(defn- load-file
  [file-name]
  (->>
   file-name
   rh/read-lines
   (map rh/to-int)
   (into [])))

(defn- count-larger
  "Count the elements that are larger than the preceding element."
  [v]
  (->>
   v
   ;; %1 is the index; %2 is the element.
   ;; Keep when the index > 0 and the element > prev element.
   ;; We end up with a seq of indexes that we can count.
   ;; We could as easily use `true` instead of the index, but the 
   ;; actual indexes might be useful for debugging.
   (keep-indexed #(when (and (> %1 0) (> %2 (v (dec %1)))) %1))
   count))

(defn part-1
  "How many measurements are larger than the previous measurement?"
  [file-name]
  (->>
   file-name
   rh/read-lines
   (map rh/to-int)
   (into [])
   count-larger))

;; class Day01
;;   # "How many measurements are larger than the previous measurement?"
;;   def part_1(file_name)
;;     lines = File.readlines(file_name, chomp: true).map &:to_i
;;     count_larger lines
;;   end

;;   # Consider sums of a three-measurement sliding window. How many sums are larger than the previous sum?
;;   def part_2(file_name)
;;     lines = File.readlines(file_name, chomp: true).map &:to_i
;;     windows = lines.each_cons(3).to_a.map &:sum
;;     count_larger windows
;;   end

;;   # -----------------------------------------------------------------
;;   private

;;   def count_larger(ary)
;;     ary
;;       .filter.with_index { |nxt, i| i > 0 && nxt > ary[i - 1] }
;;       .size
;;   end
;; end        