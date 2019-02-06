(ns advent2018.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

; To make sure you didn't miss any, you scan the likely candidate boxes again, counting the number that have an ID
; containing exactly two of any letter and then separately counting those with exactly three of any letter.
; You can multiply those two counts together to get a rudimentary checksum and compare it to what your device predicts.
;
;For example, if you see the following box IDs:
;
;abcdef contains no letters that appear exactly two or three times.
;bababc contains two a and three b, so it counts for both.
;abbcde contains two b, but no letter appears exactly three times.
;abcccd contains three c, but no letter appears exactly two times.
;aabcdd contains two a and two d, but it only counts once.
;abcdee contains two e.
;ababab contains three a and three b, but it only counts once.
;Of these box IDs, four of them contain a letter which appears exactly twice, and three of them contain a letter which appears exactly three times. Multiplying these together produces a checksum of 4 * 3 = 12.

(def problem-input
  (->> "day2.txt"
       (io/resource)
       (slurp)
       (string/trim)
       (string/split-lines)))

(defn map-values [f kv]
  (into {} (for [[k v] kv] [k (f v)])))

(defn count-letters [input]
  (->> input
       (seq)
       (group-by identity)
       (map-values count)))

(defn has-n-reps?
  [n xs]
  (boolean
    (some #(= % n) xs)))

(defn repetitions [n input]
  (->> input
       (count-letters)
       (vals)
       (has-n-reps? n)))

(defn checksum [input]
  (reduce
    (fn [[count2 count3] string]
      (let [newcount2 (if (repetitions 2 string)
                       (inc count2)
                       count2)]
      (let [newcount3 (if (repetitions 3 string)
                       (inc count3)
                       count3)]
      [newcount2 newcount3])))
    [0 0]
    input))

(defn day2
  []
  (let [[twos threes] (checksum problem-input)]
    (* twos threes)))
