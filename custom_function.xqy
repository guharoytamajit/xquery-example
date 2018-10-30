declare function local:discount($price as xs:decimal?,$percentDiscount as xs:decimal?)
as xs:decimal? {
   let $discount := $price - ($price * $percentDiscount div 100) 
   return $discount
};

let $originalPrice := 100

let $discountAvailed := 10

return ( local:discount($originalPrice, $discountAvailed)) 