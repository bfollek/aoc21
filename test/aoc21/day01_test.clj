(ns aoc21.day01-test
  (:require [clojure.test :as t]
            [aoc21.day01 :as day01]))

(t/deftest day01-happy-path
  (t/testing "part-1."
    (t/is (= 1475 (day01/part-1 "data/day01.txt"))))
  (t/testing "part-2."
    (t/is (= 1516 (day01/part-2 "data/day01.txt")))))