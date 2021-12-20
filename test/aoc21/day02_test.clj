(ns aoc21.day02-test
  (:require [clojure.test :as t]
            [aoc21.day02 :as day02]))

(t/deftest day02-happy-path
  (t/testing "part-1."
    (t/is (= 1990000 (day02/part-1 "data/day02.txt"))))
  (t/testing "part-2."
    (t/is (= 1975421260 (day02/part-2 "data/day02.txt")))))

(t/deftest direction-exception
  (t/testing "direction exception."
    (t/is (thrown-with-msg? IllegalArgumentException #"Unknown direction" (day02/part-1 "data/day02-direction-exception.txt"))))
  (t/is (thrown-with-msg? IllegalArgumentException #"Unknown direction" (day02/part-2 "data/day02-direction-exception.txt"))))