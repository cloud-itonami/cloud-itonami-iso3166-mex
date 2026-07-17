(ns culture.facts
  "Country-level regional-culture catalog for Mexico (MEX) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"MEX"
   [{:culture/id "mex.dish.taco"
     :culture/name "Taco"
     :culture/country "MEX"
     :culture/kind :dish
     :culture/summary "Traditional Mexican dish of a small hand-sized corn- or wheat-based tortilla topped with a filling; it originated in Mexico as street food."
     :culture/url "https://en.wikipedia.org/wiki/Taco"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.dish.mole"
     :culture/name "Mole"
     :culture/country "MEX"
     :culture/kind :dish
     :culture/summary "Traditional sauce and marinade originally used in Mexican cuisine, with regional varieties notably in Puebla and Oaxaca."
     :culture/url "https://en.wikipedia.org/wiki/Mole_(sauce)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.dish.pozole"
     :culture/name "Pozole"
     :culture/country "MEX"
     :culture/kind :dish
     :culture/summary "Traditional soup or stew from Mexican cuisine made with hominy and meat, originating in Mesoamerica in the pre-Columbian era."
     :culture/url "https://en.wikipedia.org/wiki/Pozole"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.dish.guacamole"
     :culture/name "Guacamole"
     :culture/country "MEX"
     :culture/kind :dish
     :culture/summary "Avocado-based dip, spread, or salad first developed in Mexico."
     :culture/url "https://en.wikipedia.org/wiki/Guacamole"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.dish.tamale"
     :culture/name "Tamale"
     :culture/name-local "Tamal"
     :culture/country "MEX"
     :culture/kind :dish
     :culture/summary "Traditional Mesoamerican dish of steamed nixtamalized-corn dough (masa) wrapped in corn husks or banana leaves, with origins in Mexico and Central America."
     :culture/url "https://en.wikipedia.org/wiki/Tamale"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.beverage.tequila"
     :culture/name "Tequila"
     :culture/country "MEX"
     :culture/kind :beverage
     :culture/summary "Distilled beverage made from the blue agave plant, primarily in the area surrounding the town of Tequila in Jalisco, Mexico, holding protected designation of origin status."
     :culture/url "https://en.wikipedia.org/wiki/Tequila"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.beverage.mezcal"
     :culture/name "Mezcal"
     :culture/country "MEX"
     :culture/kind :beverage
     :culture/summary "Distilled alcoholic beverage made from agave, originating in Mexico; some 90% of Mexican mezcal is produced in the state of Oaxaca."
     :culture/url "https://en.wikipedia.org/wiki/Mezcal"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.craft.talavera"
     :culture/name "Talavera pottery"
     :culture/country "MEX"
     :culture/kind :craft
     :culture/summary "Mexican majolica pottery tradition of Puebla and Tlaxcala, recognized by UNESCO in 2019 on the Representative List of the Intangible Cultural Heritage of Humanity."
     :culture/url "https://en.wikipedia.org/wiki/Talavera_pottery"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.festival.day-of-the-dead"
     :culture/name "Day of the Dead"
     :culture/name-local "Día de los Muertos"
     :culture/country "MEX"
     :culture/kind :festival
     :culture/summary "Holiday honouring deceased relatives, celebrated on 1-2 November and widely observed in Mexico; recognized by UNESCO as Intangible Cultural Heritage of Humanity in 2008."
     :culture/url "https://en.wikipedia.org/wiki/Day_of_the_Dead"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "mex.heritage.teotihuacan"
     :culture/name "Teotihuacan"
     :culture/name-local "Teotihuacán"
     :culture/country "MEX"
     :culture/kind :heritage
     :culture/summary "Ancient Mesoamerican city northeast of Mexico City, renowned for its massive pyramids and designated a UNESCO World Heritage Site in 1987."
     :culture/url "https://en.wikipedia.org/wiki/Teotihuacan"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-mex culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "MEX"))
                 " MEX entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
