(ns aoc21.day04
  (:require
   [clojure.string :as str]
   [rabbithole.core :as rh]))

(def board-size 5)

(defrecord Bingo [numbers boards])

(defn load-game
  [file-name]
  (let [lines (rh/read-lines file-name)
        numbers (str/split (nth lines 0) #",")
        ;; Skip the `numbers` line, and ignore the blank lines.
        board-lines (filter #(not (str/blank? %1)) (drop 1 lines))
        boards (partition board-size board-lines)]
    (->Bingo numbers boards)))

(defn part-1
  [file-name]
  nil)

(defn part-2
  [file-name]
  nil)