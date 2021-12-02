(ns aoc21.day02
  (:require
   [clojure.string :as str]
   [rabbithole.core :as rh]))

(defrecord Move [direction distance])

(defrecord Position [horizontal depth])

(defrecord AimedPosition [aim horizontal depth])

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

(defn make-move
  [pos move]
  (let [delta (:distance move)]
    (case (:direction move)
      "forward" (assoc pos :horizontal (+ (:horizontal pos) delta))
      "up" (assoc pos :depth (- (:depth pos) delta))
      "down" (assoc pos :depth (+ (:depth pos) delta))
      (throw (Exception. (str "Unknown direction:" (:direction move)))))))

;; (defn make-aimed-move
;;   [pos move]
;;   (let [delta (:distance move)]
;;     (case (:direction move)
;;       "forward" (assoc pos :horizontal (+ (:horizontal pos) delta)
;;                        :depth (+ (:depth pos) (* (:aim pos) delta)))
;;       "up" (assoc pos :aim (- (:aim pos) delta))
;;       "down" (assoc pos :aim (+ (:aim pos) delta))
;;       (throw (Exception. (str "Unknown direction:" (:direction move)))))))

(defn make-aimed-move
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