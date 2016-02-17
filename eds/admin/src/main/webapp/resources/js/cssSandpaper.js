function RuleList(e){var r=this;r.values=new Array,r.propertyName=e,r.add=function(e,t){r.values.push(new CSSRule(e,r.propertyName,t))}}function CSSRule(e,r,t){var n=this;n.selector=e,n.name=r,n.value=t,n.toString=function(){return StringHelpers.sprintf("%s { %s: %s}",n.selector,n.name,n.value)}}function MSFilterList(e){function r(){var r=a.filter.match(n);if(null!=r)for(var i=0;i<r.length;i++){var s=r[i];t.list.push(new MSFilter(e,s))}}var t=this;t.list=new Array,t.node=e;var n=/[\s\S]*\([\s\S]*\)/g,a=e.style;t.toString=function(){for(var e=new StringBuffer,r=0;r<t.list.length;r++)e.append(t.list[r].toString()),r<t.list.length-1&&e.append(",");return e.toString()},t.fixFilterStyle=function(){try{t.node.style.filter=t.toString()}catch(e){}},r()}function MSFilter(e,r){function t(){n.name=n.filterCall.match(a)[0].replace("progid:","");var e=r.split("(")[1].replace(")","");n.parameters=e.match(i);for(var t=0;t<n.parameters.length;t++)n.parameters[t]=n.parameters[t].replace("=","")}var n=this;n.node=e,n.filterCall=r;var a=/progid:([^\(]*)/g,i=/([a-zA-Z0-9]+\s*)=/g;n.toString=function(){var e=new StringBuffer;e.append(StringHelpers.sprintf("progid:%s(",n.name));for(var r=0;r<n.parameters.length;r++){var t=n.parameters[r],a=n.node.filters.item(n.name),i=a[t];"string"==typeof i?e.append(StringHelpers.sprintf('%s="%s"',t,a[t])):e.append(StringHelpers.sprintf("%s=%s",t,a[t])),r!=n.parameters.length-1&&e.append(", ")}return e.append(")"),e.toString()},t()}function StringBuffer(){var e=this,r=[];e.append=function(t){return r.push(t),e},e.appendBuffer=function(e){r=r.concat(e)},e.toString=function(){return r.join("")},e.getLength=function(){return r.length},e.flush=function(){r.length=0}}function RGBColor(e){var r=this;r.ok=!1,"#"==e.charAt(0)&&(e=e.substr(1,6)),e=e.replace(/ /g,""),e=e.toLowerCase();var t={aliceblue:"f0f8ff",antiquewhite:"faebd7",aqua:"00ffff",aquamarine:"7fffd4",azure:"f0ffff",beige:"f5f5dc",bisque:"ffe4c4",black:"000000",blanchedalmond:"ffebcd",blue:"0000ff",blueviolet:"8a2be2",brown:"a52a2a",burlywood:"deb887",cadetblue:"5f9ea0",chartreuse:"7fff00",chocolate:"d2691e",coral:"ff7f50",cornflowerblue:"6495ed",cornsilk:"fff8dc",crimson:"dc143c",cyan:"00ffff",darkblue:"00008b",darkcyan:"008b8b",darkgoldenrod:"b8860b",darkgray:"a9a9a9",darkgreen:"006400",darkkhaki:"bdb76b",darkmagenta:"8b008b",darkolivegreen:"556b2f",darkorange:"ff8c00",darkorchid:"9932cc",darkred:"8b0000",darksalmon:"e9967a",darkseagreen:"8fbc8f",darkslateblue:"483d8b",darkslategray:"2f4f4f",darkturquoise:"00ced1",darkviolet:"9400d3",deeppink:"ff1493",deepskyblue:"00bfff",dimgray:"696969",dodgerblue:"1e90ff",feldspar:"d19275",firebrick:"b22222",floralwhite:"fffaf0",forestgreen:"228b22",fuchsia:"ff00ff",gainsboro:"dcdcdc",ghostwhite:"f8f8ff",gold:"ffd700",goldenrod:"daa520",gray:"808080",green:"008000",greenyellow:"adff2f",honeydew:"f0fff0",hotpink:"ff69b4",indianred:"cd5c5c",indigo:"4b0082",ivory:"fffff0",khaki:"f0e68c",lavender:"e6e6fa",lavenderblush:"fff0f5",lawngreen:"7cfc00",lemonchiffon:"fffacd",lightblue:"add8e6",lightcoral:"f08080",lightcyan:"e0ffff",lightgoldenrodyellow:"fafad2",lightgrey:"d3d3d3",lightgreen:"90ee90",lightpink:"ffb6c1",lightsalmon:"ffa07a",lightseagreen:"20b2aa",lightskyblue:"87cefa",lightslateblue:"8470ff",lightslategray:"778899",lightsteelblue:"b0c4de",lightyellow:"ffffe0",lime:"00ff00",limegreen:"32cd32",linen:"faf0e6",magenta:"ff00ff",maroon:"800000",mediumaquamarine:"66cdaa",mediumblue:"0000cd",mediumorchid:"ba55d3",mediumpurple:"9370d8",mediumseagreen:"3cb371",mediumslateblue:"7b68ee",mediumspringgreen:"00fa9a",mediumturquoise:"48d1cc",mediumvioletred:"c71585",midnightblue:"191970",mintcream:"f5fffa",mistyrose:"ffe4e1",moccasin:"ffe4b5",navajowhite:"ffdead",navy:"000080",oldlace:"fdf5e6",olive:"808000",olivedrab:"6b8e23",orange:"ffa500",orangered:"ff4500",orchid:"da70d6",palegoldenrod:"eee8aa",palegreen:"98fb98",paleturquoise:"afeeee",palevioletred:"d87093",papayawhip:"ffefd5",peachpuff:"ffdab9",peru:"cd853f",pink:"ffc0cb",plum:"dda0dd",powderblue:"b0e0e6",purple:"800080",red:"ff0000",rosybrown:"bc8f8f",royalblue:"4169e1",saddlebrown:"8b4513",salmon:"fa8072",sandybrown:"f4a460",seagreen:"2e8b57",seashell:"fff5ee",sienna:"a0522d",silver:"c0c0c0",skyblue:"87ceeb",slateblue:"6a5acd",slategray:"708090",snow:"fffafa",springgreen:"00ff7f",steelblue:"4682b4",tan:"d2b48c",teal:"008080",metle:"d8bfd8",tomato:"ff6347",turquoise:"40e0d0",violet:"ee82ee",violetred:"d02090",wheat:"f5deb3",white:"ffffff",whitesmoke:"f5f5f5",yellow:"ffff00",yellowgreen:"9acd32"};for(var n in t)e==n&&(e=t[n]);for(var a=[{re:/^rgb\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3})\)$/,example:["rgb(123, 234, 45)","rgb(255,234,245)"],process:function(e){return[parseInt(e[1]),parseInt(e[2]),parseInt(e[3])]}},{re:/^(\w{2})(\w{2})(\w{2})$/,example:["#00ff00","336699"],process:function(e){return[parseInt(e[1],16),parseInt(e[2],16),parseInt(e[3],16)]}},{re:/^(\w{1})(\w{1})(\w{1})$/,example:["#fb0","f0f"],process:function(e){return[parseInt(e[1]+e[1],16),parseInt(e[2]+e[2],16),parseInt(e[3]+e[3],16)]}},{re:/^rgba\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3}),\s*(0{0,1}\.\d{1,}|0\.{0,}0*|1\.{0,}0*)\)$/,example:["rgba(123, 234, 45, 22)","rgba(255, 234,245, 34)"],process:function(e){return[parseInt(e[1]),parseInt(e[2]),parseInt(e[3]),parseFloat(e[4])]}}],i=0;i<a.length;i++){var s=a[i].re,o=a[i].process,l=s.exec(e);l&&(channels=o(l),r.r=channels[0],r.g=channels[1],r.b=channels[2],r.a=channels[3],r.ok=!0)}r.r=r.r<0||isNaN(r.r)?0:r.r>255?255:r.r,r.g=r.g<0||isNaN(r.g)?0:r.g>255?255:r.g,r.b=r.b<0||isNaN(r.b)?0:r.b>255?255:r.b,r.a=isNaN(r.a)?1:r.a>255?255:r.a<0?0:r.a,r.toRGB=function(){return"rgb("+r.r+", "+r.g+", "+r.b+")"},r.toRGBA=function(){return"rgba("+r.r+", "+r.g+", "+r.b+", "+r.a+")"},r.toHSV=function(){var e,t,n=r.r/255,a=r.g/255,i=r.b/255,s=Math.max(n,a,i),o=Math.min(n,a,i),l=s,f=s-o;if(t=0==s?0:f/s,s==o)e=0;else{switch(s){case n:e=(a-i)/f+(i>a?6:0);break;case a:e=(i-n)/f+2;break;case i:e=(n-a)/f+4}e/=6}return{h:e,s:t,v:l}},r.toHex=function(){var e=r.r.toString(16),t=r.g.toString(16),n=r.b.toString(16),a=Math.floor(255*r.a).toString(16);return 1==e.length&&(e="0"+e),1==t.length&&(t="0"+t),1==n.length&&(n="0"+n),"ff"==a?a="":1==a.length&&(a="0"+a),"#"+a+e+t+n}}document.querySelectorAll||(document.querySelectorAll=cssQuery);var cssSandpaper=new function(){function e(){var e=l("opacity").values;for(var r in e)for(var t=e[r],n=document.querySelectorAll(t.selector),a=0;a<n.length;a++)u.setOpacity(n[a],t.value)}function r(){var e=l("-sand-transform").values;CSS3Helpers.findProperty(document.body,"transform");for(var r in e)for(var t=e[r],n=document.querySelectorAll(t.selector),a=0;a<n.length;a++)u.setTransform(n[a],t.value)}function t(){var e=l("-sand-box-shadow").values;for(var r in e)for(var t=e[r],n=document.querySelectorAll(t.selector),a=0;a<n.length;a++)u.setBoxShadow(n[a],t.value)}function n(){var e=l("background").values.concat(l("background-image").values);CSS3Helpers.reportGradientSupport();for(var r in e)for(var t=e[r],n=document.querySelectorAll(t.selector),a=0;a<n.length;a++)u.setGradient(n[a],t.value)}function a(e){for(var r=new StringBuffer,t=0;t<e.length;t++)r.append(StringHelpers.sprintf("color-stop(%s, %s)",e[t].stop,e[t].color)),t<e.length-1&&r.append(", ");return r.toString()}function i(e){var r;switch(e.nodeName.toLowerCase()){case"style":r=StringHelpers.uncommentHTML(e.innerHTML);break;case"link":var t=XMLHelpers.getXMLHttpRequest(e.href,null,"GET",null,!1);r=t.responseText}return r=r.replace(S,"").replace(b,"")}function s(){c=document.querySelectorAll('style, link[rel="stylesheet"]');for(var e=0;e<c.length;e++)CSSHelpers.isMemberOfClass(c[e],"cssSandpaper-noIndex")||g.push(i(c[e]))}function o(){for(var e=0;e<g.length;e++){var r=g[e];if(rules=r.match(h),rules)for(var t=0;t<rules.length;t++)for(var n=rules[t].split(m),a=n[0].trim(),i=n[1],s=i.split(";"),o=0;o<s.length;o++)if(""!=s[o].trim()){var l=s[o].split(":"),f=l[0].trim().toLowerCase(),c=l[1];v[f]||(v[f]=new RuleList(f)),c&&v[f].add(a,c.trim())}}}function l(e){var r=v[e];return r||(r=new RuleList(e)),r}function f(){for(var e=document.getElementsByTagName("html")[0],r=["transform","opacity"],t=0;t<r.length;t++){var n=r[t];CSS3Helpers.supports(n)&&CSSHelpers.addClass(e,"cssSandpaper-"+n)}}var c,p,d,u=this,g=new Array,h=/[^\{]*{[^\}]*}/g,m=/[\{\}]/g,S=/\/\/.+?(?=\n|\r|$)|\/\*[\s\S]+?\*\//g,b=/@[^\{\};]*;|@[^\{\};]*\{[^\}]*\}/g,v=new Array;u.init=function(){d=document.body,p=document.createElement("div"),s(),o(),r(),t(),n(),e(),f()},u.setOpacity=function(e,r){var t=CSS3Helpers.findProperty(document.body,"opacity");if("filter"==t){e.style.zoom="100%";var n=CSS3Helpers.addFilter(e,"DXImageTransform.Microsoft.Alpha",StringHelpers.sprintf("opacity=%d",100*r));n.opacity=100*r}else null!=e.style[t]&&(e.style[t]=r)},u.setTransform=function(e,r){var t=CSS3Helpers.findProperty(e,"transform");if("filter"==t){var n=CSS3Helpers.getTransformationMatrix(r);CSS3Helpers.setMatrixFilter(e,n)}else null!=e.style[t]&&(e.style[t]=r)},u.setBoxShadow=function(e,r){var t=CSS3Helpers.findProperty(e,"boxShadow"),n=CSS3Helpers.getBoxShadowValues(r);if("filter"==t){var a=CSS3Helpers.addFilter(e,"DXImageTransform.Microsoft.DropShadow",StringHelpers.sprintf("color=%s,offX=%d,offY=%d",n.color,n.offsetX,n.offsetY));a.color=n.color,a.offX=n.offsetX,a.offY=n.offsetY}else null!=e.style[t]&&(e.style[t]=r)},u.setGradient=function(e,r){var t=CSS3Helpers.reportGradientSupport(),n=CSS3Helpers.getGradient(r);if(null!=n)if(e.filters){if(2==n.colorStops.length&&0==n.colorStops[0].stop&&1==n.colorStops[1].stop){var i=new RGBColor(n.colorStops[0].color),s=new RGBColor(n.colorStops[1].color);i=i.toHex(),s=s.toHex();var o=CSS3Helpers.addFilter(e,"DXImageTransform.Microsoft.Gradient",StringHelpers.sprintf("GradientType = %s, StartColorStr = '%s', EndColorStr = '%s'",n.IEdir,i,s));o.GradientType=n.IEdir,o.StartColorStr=i,o.EndColorStr=s}}else if(t==implementation.MOZILLA)e.style.backgroundImage=StringHelpers.sprintf("-moz-gradient( %s, %s, from(%s), to(%s))",n.dirBegin,n.dirEnd,n.colorStops[0].color,n.colorStops[1].color);else if(t==implementation.WEBKIT){var l=StringHelpers.sprintf("-webkit-gradient(%s, %s, %s %s, %s %s)",n.type,n.dirBegin,n.r0?n.r0+", ":"",n.dirEnd,n.r1?n.r1+", ":"",a(n.colorStops));e.style.backgroundImage=l}else if(t==implementation.WORKAROUND)try{CSS3Helpers.applyCanvasGradient(e,n)}catch(f){}}},MatrixGenerator=new function(){function e(e){return(e-360)*Math.PI/180}function r(r){var a=parseFloat(r),i=r.match(n);if(1!=i.length||0==a)return 0;i=i[0];var s;switch(i){case"deg":s=e(a);break;case"rad":s=a;break;default:return t.identity}return s}var t=this,n=/[a-z]+$/;t.identity=$M([[1,0,0],[0,1,0],[0,0,1]]),t.prettyPrint=function(e){return StringHelpers.sprintf("| %s %s %s | - | %s %s %s | - |%s %s %s|",e.e(1,1),e.e(1,2),e.e(1,3),e.e(2,1),e.e(2,2),e.e(2,3),e.e(3,1),e.e(3,2),e.e(3,3))},t.rotate=function(e){var t=r(e);return Matrix.RotationZ(t)},t.scale=function(e,r){return e=parseFloat(e),r=r?parseFloat(r):e,$M([[e,0,0],[0,r,0],[0,0,1]])},t.scaleX=function(e){return t.scale(e,1)},t.scaleY=function(e){return t.scale(1,e)},t.skew=function(e,t){var n,a=r(e);return n=null!=t?r(t):a,$M([[1,Math.tan(a),0],[Math.tan(n),1,0],[0,0,1]])},t.skewX=function(e){return t.skew(e,0)},t.skewY=function(e){return t.skew(0,e)},t.translate=function(e,r){var t=parseInt(e),n=parseInt(r);return $M([[1,0,t],[0,1,n],[0,0,1]])},t.translateX=function(e){return t.translate(e,0)},t.translateY=function(e){return t.translate(0,e)},t.matrix=function(e,r,t,n,a,i){return $M([[e,t,a],[r,n,i],[0,0,1]])}},CSS3Helpers=new function(){function swapIndices(e,r,t){var n=e[r];e[r]=e[t],e[t]=n}function parseColorStop(e,r){var t=new Object,n=me.getBracketedSubstring(e,"color-stop"),a=me.getBracketedSubstring(e,"from"),i=me.getBracketedSubstring(e,"to");if(n){var s=n.split(",");t.stop=normalizePercentage(s[0].trim()),t.color=s[1].trim()}else if(a)t.stop=0,t.color=a.trim();else if(i)t.stop=1,t.color=i.trim();else{if(!(1>=r))throw StringHelpers.sprintf('invalid argument "%s"',e);t.color=e,0==r?t.stop=0:t.stop=1}return t}function normalizePercentage(e){return"%"==e.substring(e.length-1,e.length)?parseFloat(e)/100+"":e}function hasIETransformWorkaround(e){return CSSHelpers.isMemberOfClass(e.parentNode,"IETransformContainer")}function addIETransformWorkaround(e){if(!hasIETransformWorkaround(e)){var r,t=e.parentNode,n=document.createElement("div");CSSHelpers.addClass(n,"IETransformContainer"),n.style.width=e.offsetWidth+"px",n.style.height=e.offsetHeight+"px",n.xOriginalWidth=e.offsetWidth,n.xOriginalHeight=e.offsetHeight,n.style.position="absolute",n.style.zIndex=e.currentStyle.zIndex;var a=0,i=0;"block"==e.currentStyle.display?(n.style.left=e.offsetLeft+13-a+"px",n.style.top=e.offsetTop+13+-i+"px"):(n.style.left=e.offsetLeft+"px",n.style.top=e.offsetTop+"px"),e.style.top="auto",e.style.left="auto",e.style.bottom="auto",e.style.right="auto";var s=e.cloneNode(!0);s.style.visibility="hidden",e.replaceNode(s),e.style.position="absolute",n.appendChild(e),t.insertBefore(n,s),n.style.backgroundColor="transparent",n.style.padding="0",r=me.addFilter(e,"DXImageTransform.Microsoft.Matrix","sizingMethod='auto expand', M11=1, M12=0, M21=0, M22=1");e.currentStyle.backgroundImage.split('"')[1]}}function degreesToRadians(e){return(e-360)*Math.PI/180}var me=this,reTransformListSplitter=/[a-z]+\([^\)]*\)\s*/g,reLeftBracket=/\(/g,reRightBracket=/\)/g,reComma=/,/g,reSpaces=/\s+/g,reFilterNameSplitter=/progid:([^\(]*)/g,reLinearGradient,canvas,cache=new Array;me.supports=function(e){return null!=CSS3Helpers.findProperty(document.body,e)?!0:!1},me.getCanvas=function(){return canvas?canvas:canvas=document.createElement("canvas")},me.getTransformationMatrix=function(CSS3TransformProperty){for(var transforms=CSS3TransformProperty.match(reTransformListSplitter),resultantMatrix=MatrixGenerator.identity,j=0;j<transforms.length;j++){var transform=transforms[j];transform=transform.replace(reLeftBracket,'("').replace(reComma,'", "').replace(reRightBracket,'")');try{var matrix=eval("MatrixGenerator."+transform);resultantMatrix=resultantMatrix.x(matrix)}catch(ex){}}return resultantMatrix},me.getBoxShadowValues=function(e){var r=new Object,t=e.split(reSpaces);return"inset"==t[0]?(r.inset=!0,t=t.reverse().pop().reverse()):r.inset=!1,r.offsetX=parseInt(t[0]),r.offsetY=parseInt(t[1]),t.length>3&&(r.blurRadius=t[2],t.length>4&&(r.spreadRadius=t[3])),r.color=t[t.length-1],r},me.getGradient=function(e){var r=new Object;r.colorStops=new Array;var t=me.getBracketedSubstring(e,"-sand-gradient");if(void 0==t)return null;var n=t.match(/[^\(,]+(\([^\)]*\))?[^,]*/g);if(r.type=n[0].trim(),"linear"==r.type){r.dirBegin=n[1].trim(),r.dirEnd=n[2].trim();for(var a=r.dirBegin.split(reSpaces),i=r.dirEnd.split(reSpaces),s=3;s<n.length;s++)r.colorStops.push(parseColorStop(n[s].trim(),s-3));if(document.body.filters){if(r.x0==r.x1)switch(a[1]){case"top":r.IEdir=0;break;case"bottom":swapIndices(r.colorStops,0,1),r.IEdir=0}if(r.y0==r.y1)switch(a[0]){case"left":r.IEdir=1;break;case"right":r.IEdir=1,swapIndices(r.colorStops,0,1)}}}else{if(document.body.filters)return null;r.dirBegin=n[1].trim(),r.r0=n[2].trim(),r.dirEnd=n[3].trim(),r.r1=n[4].trim();for(var a=r.dirBegin.split(reSpaces),i=r.dirEnd.split(reSpaces),s=5;s<n.length;s++)r.colorStops.push(parseColorStop(n[s].trim(),s-5))}return r.x0=a[0],r.y0=a[1],r.x1=i[0],r.y1=i[1],r},me.reportGradientSupport=function(){if(!cache.gradientSupport){var e,r=document.createElement("div");if(r.style.cssText="background-image:-webkit-gradient(linear, 0% 0%, 0% 100%, from(red), to(blue));",r.style.backgroundImage)e=implementation.WEBKIT;else{var t=CSS3Helpers.getCanvas();e=t.getContext&&t.toDataURL?implementation.WORKAROUND:implementation.NONE}cache.gradientSupport=e}return cache.gradientSupport},me.getBracketedSubstring=function(e,r){var t=e.indexOf(r+"(");if(-1!=t){for(var n=e.substring(t),a=1,i=r.length+1;100>i||i<n.length;i++){var s=n.substring(i,i+1);switch(s){case"(":a++;break;case")":a--}if(0==a)break}return n.substring(t+r.length+1,i)}},me.setMatrixFilter=function(e,r){hasIETransformWorkaround(e)||addIETransformWorkaround(e);var t=e.parentNode;filter=e.filters.item("DXImageTransform.Microsoft.Matrix"),filter.M11=r.e(1,1),filter.M12=r.e(1,2),filter.M21=r.e(2,1),filter.M22=r.e(2,2);var n,a=parseFloat(t.xOriginalWidth),i=parseFloat(t.xOriginalHeight);n="inline"==e.currentStyle.display?0:13,t.style.marginLeft=(a-e.offsetWidth)/2-n+r.e(1,3)+"px",t.style.marginTop=(i-e.offsetHeight)/2-n+r.e(2,3)+"px"},me.addFilter=function(e,r,t){var n;try{n=e.filters.item(r)}catch(a){var i=new MSFilterList(e);i.fixFilterStyle();var s=", ";0==e.filters.length&&(s=""),e.style.filter+=StringHelpers.sprintf("%sprogid:%s(%s)",s,r,t),n=e.filters.item(r)}return n},me.findProperty=function(e,r){capType=r.capitalize();var t=cache[r];if(!t){for(var n=e.style,a=[r,"Moz"+capType,"Webkit"+capType,"O"+capType,"filter"],i=0;i<a.length;i++)if(null!=n[a[i]]){t=a[i];break}"filter"==t&&void 0==document.body.filters&&(t=null),cache[r]=t}return t},me.parseCoordinate=function(e,r){switch(e){case"top":case"left":return 0;case"bottom":case"right":return r;case"center":return r/2}if(e=-1!=e.indexOf("%")?parseFloat(e.substr(0,e.length-1))/100*r:parseFloat(e),isNaN(e))throw Error("Unable to parse coordinate: "+e);return e},me.applyCanvasGradient=function(e,r){var t=me.getCanvas(),n=document.defaultView.getComputedStyle(e,null);t.width=parseInt(n.width)+parseInt(n.paddingLeft)+parseInt(n.paddingRight)+1,t.height=parseInt(n.height)+parseInt(n.paddingTop)+parseInt(n.paddingBottom)+2;var a,i=t.getContext("2d");a="linear"==r.type?i.createLinearGradient(me.parseCoordinate(r.x0,t.width),me.parseCoordinate(r.y0,t.height),me.parseCoordinate(r.x1,t.width),me.parseCoordinate(r.y1,t.height)):i.createRadialGradient(me.parseCoordinate(r.x0,t.width),me.parseCoordinate(r.y0,t.height),r.r0,me.parseCoordinate(r.x1,t.width),me.parseCoordinate(r.y1,t.height),r.r1);for(var s=0;s<r.colorStops.length;s++){var o=r.colorStops[s];a.addColorStop(o.stop,o.color)}i.fillStyle=a,i.fillRect(0,0,t.width,t.height),e.style.backgroundImage="url('"+t.toDataURL()+"')"}},implementation=new function(){this.NONE=0,this.MOZILLA=1,this.WEBKIT=2,this.IE=3,this.OPERA=4,this.WORKAROUND=5},StringHelpers=new function(){var e=this;e.initWhitespaceRe=/^\s\s*/,e.endWhitespaceRe=/\s\s*$/,e.whitespaceRe=/\s/,e.sprintf=function(e){var r=function(e,r,t){for(var n="",a=0;a<Math.abs(t);a++)n+=r;return t>0?e+n:n+e},t=function(e,t,n,a){var i=function(e,r,t){return r>=0?e.indexOf(" ")>=0?t=" "+t:e.indexOf("+")>=0&&(t="+"+t):t="-"+t,t},s=parseInt(t,10);if("0"==t.charAt(0)){var o=0;return(e.indexOf(" ")>=0||e.indexOf("+")>=0)&&o++,n.length<s-o&&(n=r(n,"0",n.length-(s-o))),i(e,a,n)}return n=i(e,a,n),n.length<s&&(n=e.indexOf("-")<0?r(n," ",n.length-s):r(n," ",s-n.length)),n},n=new Array;n.c=function(e,r,t,n){return"number"==typeof n?String.fromCharCode(n):"string"==typeof n?n.charAt(0):""},n.d=function(e,r,t,a){return n.i(e,r,t,a)},n.u=function(e,r,t,a){return n.i(e,r,t,Math.abs(a))},n.i=function(e,n,a,i){var s=parseInt(a),o=Math.abs(i).toString().split(".")[0];return o.length<s&&(o=r(o," ",s-o.length)),t(e,n,o,i)},n.E=function(e,r,t,a){return n.e(e,r,t,a).toUpperCase()},n.e=function(e,r,n,a){return iPrecision=parseInt(n),isNaN(iPrecision)&&(iPrecision=6),rs=Math.abs(a).toExponential(iPrecision),rs.indexOf(".")<0&&e.indexOf("#")>=0&&(rs=rs.replace(/^(.*)(e.*)$/,"$1.$2")),t(e,r,rs,a)},n.f=function(e,r,n,a){return iPrecision=parseInt(n),isNaN(iPrecision)&&(iPrecision=6),rs=Math.abs(a).toFixed(iPrecision),rs.indexOf(".")<0&&e.indexOf("#")>=0&&(rs+="."),t(e,r,rs,a)},n.G=function(e,r,t,a){return n.g(e,r,t,a).toUpperCase()},n.g=function(e,r,n,a){return iPrecision=parseInt(n),absArg=Math.abs(a),rse=absArg.toExponential(),rsf=absArg.toFixed(6),isNaN(iPrecision)||(rsep=absArg.toExponential(iPrecision),rse=rsep.length<rse.length?rsep:rse,rsfp=absArg.toFixed(iPrecision),rsf=rsfp.length<rsf.length?rsfp:rsf),rse.indexOf(".")<0&&e.indexOf("#")>=0&&(rse=rse.replace(/^(.*)(e.*)$/,"$1.$2")),rsf.indexOf(".")<0&&e.indexOf("#")>=0&&(rsf+="."),rs=rse.length<rsf.length?rse:rsf,t(e,r,rs,a)},n.o=function(e,n,a,i){var s=parseInt(a),o=Math.round(Math.abs(i)).toString(8);return o.length<s&&(o=r(o," ",s-o.length)),e.indexOf("#")>=0&&(o="0"+o),t(e,n,o,i)},n.X=function(e,r,t,a){return n.x(e,r,t,a).toUpperCase()},n.x=function(e,n,a,i){var s=parseInt(a);i=Math.abs(i);var o=Math.round(i).toString(16);return o.length<s&&(o=r(o," ",s-o.length)),e.indexOf("#")>=0&&(o="0x"+o),t(e,n,o,i)},n.s=function(e,r,n,a){var i=parseInt(n),s=a;return s.length>i&&(s=s.substring(0,i)),t(e,r,s,0)},farr=e.split("%"),retstr=farr[0],fpRE=/^([-+ #]*)(\d*)\.?(\d*)([cdieEfFgGosuxX])(.*)$/;for(var a=1;a<farr.length;a++)fps=fpRE.exec(farr[a]),fps&&(null!=arguments[a]&&(retstr+=n[fps[4]](fps[1],fps[2],fps[3],arguments[a])),retstr+=fps[5]);return retstr},e.uncommentHTML=function(e){return-1!=e.indexOf("-->")&&-1!=e.indexOf("<!--")?e.replace("<!--","").replace("-->",""):e}},XMLHelpers=new function(){var e=this;e.getXMLHttpRequest=function(r,t){var n,a=e.getXMLHttpRequest.arguments,i=e.getXMLHttpRequest.arguments.length,s=i>2?a[2]:"GET",o=i>3?a[3]:"",l=i>4?a[4]:!0;if(window.XMLHttpRequest)n=new XMLHttpRequest;else{if(!window.ActiveXObject)return null;try{n=new ActiveXObject("Msxml2.XMLHTTP")}catch(f){n=new ActiveXObject("Microsoft.XMLHTTP")}}return l&&(n.onreadystatechange=t),"GET"==s&&""!=o&&(r+="?"+o),n.open(s,r,l),n.setRequestHeader("If-Modified-Since","Sat, 1 Jan 2000 00:00:00 GMT"),n.send(o),n}},CSSHelpers=new function(){function e(e){return"\\s"+e+"\\s|^"+e+"\\s|\\s"+e+"$|^"+e+"$"}var r=this,t=new RegExp("\\s");r.isMemberOfClass=function(r,n){if(t.test(n))return!1;var a=new RegExp(e(n),"g");return a.test(r.className)},r.addClass=function(e,n){t.test(n)||r.isMemberOfClass(e,n)||(e.className+=" "+n)},r.removeClass=function(r,n){if(!t.test(n)){var a=new RegExp(e(n),"g"),i=r.className;r.className&&(r.className=i.replace(a,""))}}};String.prototype.trim=function(){var e=this;if(this.length>6e3){e=this.replace(StringHelpers.initWhitespaceRe,"");for(var r=e.length;StringHelpers.whitespaceRe.test(e.charAt(--r)););return e.slice(0,r+1)}return this.replace(StringHelpers.initWhitespaceRe,"").replace(StringHelpers.endWhitespaceRe,"")},String.prototype.capitalize=function(){return this.charAt(0).toUpperCase()+this.substr(1)},window.onload=function(){cssSandpaper.init()};