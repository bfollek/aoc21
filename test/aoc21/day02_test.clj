(ns aoc21.day02-test
  (:require [clojure.test :refer :all]
            [aoc21.day02 :refer :all]))

(deftest day02
  (testing "part-1."
    (is (= 1990000 (aoc21.day02/part-1 "data/day02.txt")))))

(deftest direction-exception
  (testing "direction exception."
    (is (thrown-with-msg? Exception #"Unknown direction" (aoc21.day02/part-1 "data/day02-direction-exception.txt")))))