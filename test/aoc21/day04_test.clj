(ns aoc21.day04-test
  (:require [clojure.test :as t]
            [aoc21.day04 :as day04]))

(t/deftest day04-happy-path
  (t/testing "part-1."
    (t/is (= nil (day04/part-1 "data/day04.txt"))))
  (t/testing "part-2."
    (t/is (= nil (day04/part-2 "data/day04.txt")))))