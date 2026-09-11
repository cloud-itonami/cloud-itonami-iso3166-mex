(ns statute.facts-test
  (:require [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest mex-has-spec-basis
  (let [sb (facts/spec-basis "MEX")]
    (is (= 3 (count sb)))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["MEX" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["mex.ley-federal-del-trabajo-1970"]
         (mapv :statute/id (facts/by-topic "MEX" :labor))))
  (is (empty? (facts/by-topic "MEX" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
