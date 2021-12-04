(ns aoc21.day04-test
  (:require [clojure.test :refer :all]
            [aoc21.day04 :refer :all]))

(deftest day04
  (testing "part-1."
    (is (= nil (aoc21.day04/part-1 "data/day04.txt"))))
  (testing "part-2."
    (is (= nil (aoc21.day04/part-2 "data/day04.txt")))))