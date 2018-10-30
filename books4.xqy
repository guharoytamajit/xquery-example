let $items := ('orange', <apple/>, <fruit type="juicy"/>, <vehicle type="car">sentro</vehicle>, 1,2,3,'a','b',"abc")
let $count := count($items)
return
<result>
   <count>{$count}</count>
   
   <items>
      {
	     for $item in $items
         return <item>{$item}</item>
      }
   </items>
   
</result>