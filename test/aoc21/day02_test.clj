(ns aoc21.day02-test
  (:require [clojure.test :refer :all]
            [aoc21.day02 :refer :all]))

(deftest day02
  (testing "part-1."
    (is (= 1990000 (aoc21.day02/part-1 "data/day02.txt"))))
  (testing "part-2."
    (is (= 1975421260 (aoc21.day02/part-2 "data/day02.txt")))))

(deftest direction-exception
  (testing "direction exception."
    (is (thrown-with-msg? IllegalArgumentException #"Unknown direction" (aoc21.day02/part-1 "data/day02-direction-exception.txt"))))
  (is (thrown-with-msg? IllegalArgumentException #"Unknown direction" (aoc21.day02/part-2 "data/day02-direction-exception.txt"))))