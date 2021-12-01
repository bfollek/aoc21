(ns aoc21.day01-test
  (:require [clojure.test :refer :all]
            [aoc21.day01 :refer :all]))

(deftest day01
  (testing "part-1."
    (is (= 1475 (aoc21.day01/part-1 "data/day01.txt")))))