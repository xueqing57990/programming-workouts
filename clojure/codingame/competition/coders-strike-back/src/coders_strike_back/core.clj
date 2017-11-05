(ns Player
  (:gen-class))

(defn inc-atom [curr-val] (inc curr-val))
(defn dec-atom [curr-val] (dec curr-val))

;; norme du vecteur Norme = ||(x, y) || = sqrt(x*x+y*y)
(defn norm [x y] (Math/sqrt (+ (* x x) (* y y))))
;;distance between 2 points
(defn distance [x1 y1 x2 y2] (Integer/valueOf (Math/round (Math/sqrt (+ (Math/pow (- x2 x1) 2) (Math/pow (- y2 y1) 2))))))
;; Conversion degrés en radians :
;; rad  = degrés * M_PI / 180
(defn convert-degree-to-radian [degree]
  (* degree (/ Math/PI 180)))

;; Conversion degrés en radians :
;; rad  = degrés * M_PI / 180
(defn convert-radian-to-degree [radian]
  (* radian (/ 180 Math/PI)))

;; Conversion d'un double en entier avec arrondi
(defn convert-to-int [float-value]
  (Integer/valueOf (Math/round float-value)))

;;Angle entre 2 points (x1,y1) et (x2,y2)
(defn angle-between-2-points [x1 y1 x2 y2]
  (let [d (distance x1 y1 x2 y2) dx (/ (- x2 x1) d) dy (/ (- y2 y1) d) angle (convert-radian-to-degree (Math/acos dx))]
    (if (< dy 0) (- 360.0 angle) angle)))

(defn after [val1 val2] (if (> (- val2 val1) 0) true false))
(defn before [val1 val2] (if (< (- val2 val1) 0) true false))

;;Compute a boost value between 0 and 100
(defn compute-boost [next-checkpoint-distance next-checkpoint-angle]
  (cond
    (> (Math/abs next-checkpoint-angle) 90) "0"
    (and (< next-checkpoint-distance 1000) (> (Math/abs next-checkpoint-angle) 30)) "0"
    (< next-checkpoint-distance 600) "0"
    (< 601 next-checkpoint-distance 800) "20"
    (< 801 next-checkpoint-distance 1000) "40"
    ;(< 1001 next-checkpoint-distance 1200) "60"
    ;(< 1201 next-checkpoint-distance 1400) "80"
    ;(and (< next-checkpoint-distance 1200) (> (Math/abs next-checkpoint-angle) 45)) "0"
    ;(and (< next-checkpoint-distance 1500) (> (Math/abs next-checkpoint-angle) 60)) "50"
    ;(< next-checkpoint-distance 1000) "0"
    ; (< (Math/abs next-checkpoint-angle) 30) "100"
    ; (> next-checkpoint-distance 4000) "100"
    true "100"))

(def max-shield-usage (atom 3))
(def last-shield-usage (atom 0))
(def boost-used? (atom false))
(def game-loop-counter (atom 0))

;;Compute BOOST, SHIELD or boost value between 0 and 100
(defn compute-action [next-checkpoint-x next-checkpoint-y next-checkpoint-distance next-checkpoint-angle x y opponent-x opponent-y boost-used? game-loop-counter]
  (cond
    (and (not @boost-used?) (> @game-loop-counter 3) (< (Math/abs next-checkpoint-angle) 10) (> next-checkpoint-distance 4000)) (do (reset! boost-used? true) "BOOST")
    (and (= @last-shield-usage 0) (> @game-loop-counter 10) (> @max-shield-usage 0) (< (distance opponent-x opponent-y x y) 650)) (do (reset! last-shield-usage 3) (swap! max-shield-usage dec-atom) "SHIELD")
    true (compute-boost next-checkpoint-distance next-checkpoint-angle)))


(defn -main [& args]
  (while true
    (let [x (read) y (read)
          nextCheckpointX (read) nextCheckpointY (read)
          nextCheckpointDist (read) nextCheckpointAngle (read)
          opponentX (read) opponentY (read)
          opponent-distance (distance x y opponentX opponentY)]
      ; nextCheckpointX: x position of the next check point
      ; nextCheckpointY: y position of the next check point
      ; nextCheckpointDist: distance to the next checkpoint
      ; nextCheckpointAngle: angle between your pod orientation and the direction of the next checkpoint

      (swap! game-loop-counter inc-atom)
      (if (> @last-shield-usage 0) (swap! last-shield-usage dec-atom))

      ; (binding [*out* *err*]
      ;   (println "Debug messages..."))
      ; (binding [*out* *err*]
      ;   (println (str "Has the boost been used ? : " @boost-used?))
      ;   (println (str "Next check point distance coordinates : (" nextCheckpointX "," nextCheckpointY ")"))
      ;   (println (str "Given next check point distance : " nextCheckpointDist))
      ;   (println (str "Calculated next check point distance : " (distance x y nextCheckpointX nextCheckpointY)))
      ;   (println (str "Pod angle with checkpoint : " nextCheckpointAngle))
      ;   (println (str "Calculated Pod angle with checkpoint : " (angle-between-2-points x y nextCheckpointX nextCheckpointY)))
      ;   (println (str "Pod Position : (" x "," y ")"))
      ;   (println (str "Opponent pod Position : (" opponentX "," opponentY ")"))
      ;   (println (str "Opponent distance : " opponent-distance))
      ;   (println (str "Calculated Pod angle with opponent : " (angle-between-2-points x y opponentX opponentY)))
      ;   (println (str "Max Shield Usage : " @max-shield-usage))
      ;   (println (str "Last Shield Usage : " @last-shield-usage))
      ;   (println (str "Game loop counter : " @game-loop-counter))))


      ; You have to output the target position
      ; followed by the power (0 <= thrust <= 100)
      ; i.e.: "x y thrust"
      (print nextCheckpointX)
      (print " ")
      (print nextCheckpointY)
      (print " ")
      (print (compute-action
               nextCheckpointX nextCheckpointY
               nextCheckpointDist nextCheckpointAngle
               x y
               opponentX opponentY
               boost-used? game-loop-counter)))
    (println "")))
