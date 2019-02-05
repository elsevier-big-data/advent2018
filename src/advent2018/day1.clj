(ns advent2018.day1
  (:require [clojure.java.io :as io]
            [clojure.string :refer [split-lines]]))

(defn sum-so-far
  [prev new]
  ;(println (+ prev new) "\n")
  (+ prev new))

(defn total [xs] (reductions + xs))

(defn freq-sums
  [freqs]
  (reduce
    (fn [acc x] (if (acc x)
                  (reduced x)
                  (conj acc x)))
    #{}
    freqs))

(defn parse-freqs-strs
  [filename]
  (map #(Integer/parseInt %) (split-lines (slurp (io/resource "day1.txt")))))

(defn day1
  []
  (freq-sums
    (total (cycle (parse-freqs-strs "day1.txt")))))

(day1)
; get first frequency sum that is the same as the previous frequency sum
; (use reduce + reduced to terminate when tuple (x, sums so far) where
; x is in list of sums, can use reductions to get list)



(defn thing1
  [freqs]
  (reduce sum-so-far freqs))

;(defn day1.2
;  []
;  )