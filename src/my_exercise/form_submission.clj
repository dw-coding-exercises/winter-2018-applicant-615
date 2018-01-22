;copied from home.clj
(ns my-exercise.form_submission
  (:require [hiccup.page :refer [html5]]))

; copied from home.clj
(defn header [_]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0, maximum-scale=1.0"}]
   [:title "Find my next election"]
   [:link {:rel "stylesheet" :href "default.css"}]])

; take in the params, pull the state and place out,
;	combine them with the baseURI, and return the entire finalURI lowercased.
;	finalURI would then be passed to processOCDID
(defn constructOCDID [params]
	(def baseURI "ocd-division/country:us/state:")
	(def state (get params :state))
	(def place (get params :city))
	(def finalURI (clojure.string/join "/" [baseURI state place]))
	(clojure.string/lower-case finalURI))

; call constructOCDID to get the ocd-id based on the form parameters.
;	then make the API call to Democracy Works' API
(defn processOCDID [params]
	[:div
		[:h1 "Your next elections:"]
		[:p (constructOCDID params)]])

; Submission notes:
; This is as far as I could get in 2 hrs.

; I had some difficulty getting the form params from the request.
;	I know params is defined, but for some reason my gets in constructODCID return empty.
;	I think the problem might be how I am setting up the route in defroutes, but unsure

; Of course, the first improvement I would make is actually finishing this assignment out.

; As far as extras go, I would have used an API (Google Maps?)
;	to determine the county from the address from the form, and used that to further
;	refine the results returned to the user.

; I think in terms of creating a good user experience I would have wanted to 
;	display the form again on this submission page. Submitting the form again would
;	have loaded that new set of results on this same page, so that the user could see
;	a side by side view of upcoming elections in multiple places at once.

(defn page [params]
  (html5
   (header params)
   (processOCDID params)))