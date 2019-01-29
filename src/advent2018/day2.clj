(ns advent2018.day2)

(defn map-vals
  "Return the result of applying f to the values of the map kv."
  [f kv]
  (into {} (for [[k v] kv] [k (f v)])))

(defn count-letters [s]
  (->> s
       (seq)
       (group-by identity)
       (map-vals count)))

(defn has-n-reps?
  [n xs]
  (boolean
    (some #(= % n) xs)))

(def has-two-reps? (partial has-n-reps? 2))
(def has-three-reps? (partial has-n-reps? 3))

(def has-two-three-reps
  (comp (juxt has-two-reps? has-three-reps?)
        vals
        count-letters))

(comment
  (defn count-reps
   [words]

   (-> (map count-letters words)
       (juxt))))
