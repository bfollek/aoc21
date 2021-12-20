(ns aoc21.day03-test
  (:require [clojure.test :as t]
            [aoc21.day03 :as day03]))


 ;(:require
 ;  [rabbithole.core :as rh]))

(t/deftest day03-happy-path
  (t/testing "part-1."
    (t/is (= 2640986 (day03/part-1 "data/day03.txt"))))
  (t/testing "part-2."
    (t/is (= 6822109 (day03/part-2 "data/day03.txt"))))
  (t/testing "part-2-reduced."
    (t/is (= 6822109 (day03/part-2-reduced "data/day03.txt")))))