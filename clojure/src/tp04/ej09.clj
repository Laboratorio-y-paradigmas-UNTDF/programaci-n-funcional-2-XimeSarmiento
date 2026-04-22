(ns tp04.ej09
  "Ejercicio 9 — Validadores con currying (5 pts). Trazabilidad: F-18"
  (:require [clojure.string :as str]))

;; Retorna fn que valida value con pred. Ok → {:status :ok :value val}, error → {:status :error :error msg}.
(defn make-validator [pred error-msg]
  ;; TODO: implementar — retornar (fn [value] ...)
  (fn [value]
    (if (pred value)
      {:status :ok :value value}
      {:status :error :error error-msg}))
  )

;; Aplica validators en secuencia; para en el primer error.
(defn validate-field [value & validators]
  ;; TODO: implementar con reduce
  (reduce
   (fn [acc validator]
     (if (= :error (:status acc))
       (reduced acc)
       (validator (:value acc))))
   {:status :ok :value value}
   validators)
  )

(def validate-not-empty
  ;; TODO: (make-validator ... "campo vacío")
  (make-validator
   (fn [value]
     (and (string? value) (not (str/blank? value))))
   "campo vacío")
  )

(def validate-email-format
  ;; TODO: (make-validator ... "email inválido")
  (make-validator
   (fn [value]
     (boolean (re-matches #"^[^@\s]+@[^@\s]+\.[^@\s]+$" value)))
   "email inválido")
  )
