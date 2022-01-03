(ns aoc21.day02-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc21.day02 :refer [part-1 part-2]]))

(deftest day02-happy-path
  (testing "part-1."
    (is (= 1990000 (part-1 "data/day02.txt"))))
  (testing "part-2."
    (is (= 1975421260 (part-2 "data/day02.txt")))))

(deftest direction-exception
  (testing "part-1 direction exception."
    (is (thrown-with-msg? IllegalArgumentException #"Unknown direction" (part-1 "data/day02_direction_exception.txt"))))
  (testing "part-2 direction exception."
    (is (thrown-with-msg? IllegalArgumentException #"Unknown direction" (part-2 "data/day02_direction_exception.txt")))))