(ns aoc21.day03-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc21.day03 :refer [part-1 part-2 part-2-reduced]]))

(deftest day03-happy-path
  (testing "part-1."
    (is (= 2640986 (part-1 "data/day03.txt"))))
  (testing "part-2."
    (is (= 6822109 (part-2 "data/day03.txt"))))
  (testing "part-2-reduced."
    (is (= 6822109 (part-2-reduced "data/day03.txt")))))