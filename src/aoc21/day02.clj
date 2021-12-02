(ns aoc21.day02
  (:require
   [clojure.string :as str]
   [rabbithole.core :as rh]))

(defrecord Move [direction distance])

(defn parse-move
  [s]
  (let [[direction distance] (str/split s #" ")]
    (->Move direction (rh/to-int distance))))

(defn load-moves
  [file-name]
  (->>
   file-name
   rh/read-lines
   (map parse-move)))
   ;(map rh/to-int)))