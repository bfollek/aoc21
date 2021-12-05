(ns aoc21.day04
  (:require
   [rabbithole.core :as rh]))

(defrecord Bingo [numbers boards])

(defn load-game
  [file-name]
  (->>
   file-name
   rh/read-lines))

(defn part-1
  [file-name]
  nil)

(defn part-2
  [file-name]
  nil)