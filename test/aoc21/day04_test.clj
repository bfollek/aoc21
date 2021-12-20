(ns aoc21.day04-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc21.day04 :refer [part-1 part-2]]))

(deftest day04-happy-path
  (testing "part-1."
    (is (= nil (part-1 "data/day04.txt"))))
  (testing "part-2."
    (is (= nil (part-2 "data/day04.txt")))))