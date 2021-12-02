(ns aoc21.day02-test
  (:require [clojure.test :refer :all]
            [aoc21.day02 :refer :all]))

(deftest day01
  (testing "part-1."
    (is (= 1990000 (aoc21.day02/part-1 "data/day02.txt")))))