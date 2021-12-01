(ns aoc21.day01
  (:require
   [rabbithole.core :as rh]))

;; (defn part-1
;;   "How many measurements are larger than the previous measurement?"
;;   [file-name]
;;   (let [lines (into [] (rh/read-lines file-name))]
;;     (loop [i 0 cnt 0]
;;       (if (> i (dec (count lines)))
;;         cnt
;;         (recur)))))


(defn count-larger
  "Count the elements that are larger than the preceding element."
  [v]
  ;(keep-indexed (fn [idx val] (when (and (> idx 0) (> val (v (dec idx)))) idx)) v))
  (->>
   v
   ;; When the index > 0 and the value > prev value.
   (keep-indexed #(when (and (> %1 0) (> %2 (v (dec %1)))) %1))
   count))
  ;(keep-indexed #(when (and (> %1 0) (> %2 (v (dec %1)))) %1) v))

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