(print "---------------------------- Operaciones Aritmeticas -------------------------------------")

(print (+ 5 3))
(print (- 5 3))
(print (* 5 3))
(print (/ 12 3))
(print "------------------------- Operaciones Aritmeticas de varias capas ------------------------")
(print (* (+ 3 4) 5)) 
(print (+ (* (/ 18 2) 3) 13))

(print "------------------------------------------ QUOTE -----------------------------------------")

(print (quote (+ 1 2)))
(print ('(/ 4 2)))

(print "-------------------------------------- DEFUN ---------------------------------------------")

(defun cuadrado (n) (* n n))
(print (cuadrado 9))

(print "----------------------------- SETQ ------------------------------------")

(print(setq x 7))
(print(setq y 15))
(print (+ x 3))
(print (- x 3))
(print (* x 3))
(print (/ y 3))

(print "----------------------------- ATOM, LIST, EQUAL, <, > ------------------------------------")

(print (atom 1)) ;Devuelve T porque 1 es un símbolo, que es un átomo.
(print (atom (list 1 2 3 4 5))) ;Devuelve NIL porque (1 2 3 4 5) es una lista, no un átomo.
(print (equal (list 1 2 3 4 5) (list 1 2 3 4 5))) ;Devuelve T porque son iguales las listas.
(print (equal (list 1 2 5 4 5) (list 1 2 3 4 5))) ;Devuelve NIL porque no son iguales las listas.
(print (= 1 1)) ;Devuelve T porque los valores son iguales.
(print (= 1 2)) ;Devuelve NIL porque los valores no son iguales.
(print (< 1 3)) ;Devuelve T porque 1 es menor que 3.
(print (> 5 3)) ;Devuelve T porque 5 es mayor que 3.

(print "----------------------------- COND ------------------------------------")

(print (COND ((= 1 0) 'Cero) ((< 1 0) 'Negativo) ((> 1 0) 'Positivo)))

(print "----------------------------- Paso de parámetros ------------------------------------")

;(defun fibonacci (n) (cond ((= n 0) 0) ((= n 1) 1) (t (+ (fibonacci (- n 1)) (fibonacci (- n 2))))))
;(print (fibonacci 10)) ;respuesta 55
;(defun factorial (n) (cond (= n 0) 1 (* n (factorial (- n 1)))))
;(print (factorial 5)) ;respuesta 120