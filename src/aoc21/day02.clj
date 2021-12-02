(ns aoc21.day02
  (:require
   [clojure.string :as str]
   [rabbithole.core :as rh]))

(defrecord Move [direction distance])

(defrecord Position [depth horizontal])

(defrecord AimedPosition [aim depth horizontal])

(defn- parse-move
  [s]
  (let [[direction distance] (str/split s #" ")]
    (->Move direction (rh/to-int distance))))

(defn- load-moves
  [file-name]
  (->>
   file-name
   rh/read-lines
   (map parse-move)))

(defn- make-move
  [{:keys [depth horizontal] :as pos} {:keys [direction distance]}]
  (case direction
    "forward" (assoc pos :horizontal (+ horizontal distance))
    "up" (assoc pos :depth (- depth distance))
    "down" (assoc pos :depth (+ depth distance))
    (throw (Exception. (str "Unknown direction:" direction)))))

(defn- make-aimed-move
  [{:keys [aim depth horizontal] :as pos} {:keys [direction distance]}]
  (case direction
    "forward" (assoc pos :horizontal (+ horizontal distance)
                     :depth (+ depth (* aim distance)))
    "up" (assoc pos :aim (- aim distance))
    "down" (assoc pos :aim (+ aim distance))
    (throw (Exception. (str "Unknown direction:" direction)))))

(defn part-1
  "Calculate the horizontal position and depth you would have after following the planned course. What do you get if you multiply your final horizontal position by your final depth?"
  [file-name]
  (let [moves (load-moves file-name)
        pos (reduce make-move (->Position 0 0) moves)]
    (* (:horizontal pos) (:depth pos))))

(defn part-2
  "Using this new interpretation of the commands, calculate the horizontal position and depth you would have after following the planned course. What do you get if you multiply your final horizontal position by your final depth?"
  [file-name]
  (let [moves (load-moves file-name)
        pos (reduce make-aimed-move (->AimedPosition 0 0 0) moves)]
    (* (:horizontal pos) (:depth pos))))