(ns dawkinsweasel)



(def genes (reduce #(conj %1 (char %2)) [\space] (range 65 91)))
(defn random-individual
  []
  (->> (repeatedly #(rand-nth genes))
       (take 28)
       (apply str)))


(def random-population #(take 100 (repeatedly random-individual)))

(defn compare-individuals
  [x y]
  (->> (map vector x y)
       (filter (fn [[a b]] (= a b)))
       count))

(defn fitness
  [s]
  (compare-individuals s "METHINKS IT IS LIKE A WEASEL"))

(defn fittest
  [xs]
  (last (sort-by fitness xs)))

(defn mutate-gene
  [c]
  (if (< (rand) 0.05)
    (rand-nth genes)
    c))

(defn mutate
  [s]
  (apply str (map mutate-gene s)))

(defn reproduce
  [s]
  (map mutate (take 100 (repeatedly (constantly s)))))

(defn next-generation
  [pop]
  (println (fittest pop))
  (reproduce (fittest pop)))

(defn dawkinsweasel
  [pop threshold]
  (first
    (drop-while
    #(not= (-> %1 fittest fitness) threshold)
    (iterate next-generation pop))))
