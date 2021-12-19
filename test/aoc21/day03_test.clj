(ns aoc21.day03-test
  (:require [clojure.test :refer :all]
            [aoc21.day03 :refer :all]))

(deftest day03
  (testing "part-1."
    (is (= 2640986 (aoc21.day03/part-1 "data/day03.txt"))))
  (testing "part-2."
    (is (= 6822109 (aoc21.day03/part-2 "data/day03.txt"))))
  (testing "part-2-reduced."
    (is (= 6822109 (aoc21.day03/part-2-reduced "data/day03.txt")))))