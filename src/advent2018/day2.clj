(ns advent2018.day2)

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

(def input "abcdef")

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

(def has-2-reps? (partial has-n-reps? 2))

(defn count2s [input]
  (->> input
       (count-letters)
       (vals)
       (has-2-reps?)))

(def has-3-reps? (partial has-n-reps? 3))

(defn count3s [input]
  (->> input
       (count-letters)
       (vals)
       (has-3-reps?)))

(defn checksum [input]
  (reduce
    (fn [count string]
      (if (count2s string)
        (inc count)
        count))
    0
    input))



(checksum ["abcdefe" "ffghy" "aabcd"])