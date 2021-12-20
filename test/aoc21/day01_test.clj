(ns aoc21.day01-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc21.day01 :refer [part-1 part-2]]))

(deftest day01-happy-path
  (testing "part-1."
    (is (= 1475 (part-1 "data/day01.txt"))))
  (testing "part-2."
    (is (= 1516 (part-2 "data/day01.txt")))))