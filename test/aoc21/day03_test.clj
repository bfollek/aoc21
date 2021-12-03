(ns aoc21.day03-test
  (:require [clojure.test :refer :all]
            [aoc21.day03 :refer :all]))

(deftest day03
  (testing "part-1."
    (is (= 1 (aoc21.day03/part-1 "data/day03.txt"))))
  (testing "part-2."
    (is (= 2 (aoc21.day03/part-2 "data/day03.txt")))))