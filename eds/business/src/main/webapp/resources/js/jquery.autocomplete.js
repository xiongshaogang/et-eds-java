!function(t){t.fn.extend({AutoComplete:function(e){return this.each(function(){this&&"INPUT"===this.tagName&&"text"===this.type&&(this.controller?this.controller.setOption(e):t.isPlainObject(e)&&(this.controller=new i(this,e)))})}});var i=function(i,s){this.option=t.extend(!1,{width:320,maxHeight:null,itemHeight:null,listStyle:"normal",listDirection:"down",data:[],ajaxDataType:"json",ajaxParams:{},ajaxTimeout:3e3,ajaxType:"GET",maxItems:20,matchHandler:f,emphasisHandler:y,createItemHandler:null,beforeLoadDataHandler:null,afterSelectedHandler:null,async:!1,emphasis:!0,onerror:null},s),e.apply(this,[i]),a.apply(this)},e=function(i){var e=this;this.inputView=t(i),this.inputView.attr("autocomplete","off").keyup(this._keyup=function(t){switch(t.keyCode){case 13:case 16:case 17:case 37:case 38:case 39:case 40:break;case 27:r.apply(e);break;default:e.option.async?setTimeout(function(){u.apply(e)},0):u.apply(e)}}).keydown(this._keydown=function(t){switch(t.keyCode){case 38:h.apply(e,["up"]);break;case 40:h.apply(e,["down"]);break;case 13:var i=e.searchView.is(":visible");if(l.apply(e),i)return!1}}).blur(this._blur=function(){t(document).one("click",function(){r.apply(e)})})},a=function(){var i=this;this.searchView=t("<div class='ac'><ul></ul></div>").appendTo(document.body).on("mouseenter","li",function(){i.searchView.find("li.selected").removeClass("selected"),t(this).addClass("selected")}).on("mouseleave","li",function(){t(this).removeClass("selected")}).on("click","li",function(){l.apply(i),r.apply(i)}).css("font-size",this.inputView.css("font-size")),t(window).resize(function(){o.apply(i)})},s=function(i){var e=this,a=this.searchView.find("ul").empty();if(-1==t.inArray(this.option.listStyle,["normal","iconList","custom"]))throw["遇到未知的listStyle参数！"];t.each(i,function(i,s){var o=t("<li><div></div></li>").appendTo(a).addClass(e.option.listStyle).data("data",s).find("div");switch(e.option.listStyle){case"normal":o.append("<span>"+s.label+"</span>");break;case"iconList":var n=t("<img></img>").attr("src",s.image);o.append(t("<div></div>").append(n)).append("<span>"+s.label+"</span>");break;case"custom":o.append(e.option.createItemHandler.apply(e,[i,s]));case"default":}e.option.itemHeight>0&&o.height(e.option.itemHeight).css("max-height",e.option.itemHeight)})},o=function(){if("down"===this.option.listDirection)var t=this.inputView.offset().top+this.inputView.outerHeight();else{if("up"!==this.option.listDirection)throw"遇到未知的listDirection参数！";var t=this.inputView.offset().top-this.searchView.outerHeight()}var i=this.inputView.offset().left;this.searchView.css("top",t+"px").css("left",i+"px")},n=function(){if("string"==typeof this.option.width&&"auto"===this.option.width.toLowerCase())return this.inputView.outerWidth()-2;if("number"==typeof this.option.width)return this.option.width;throw"遇到未知的width参数！"},p=function(i){var e=this;"up"===this.option.listDirection&&(i=i.reverse());try{s.apply(e,[i]),this.option.maxHeight>0&&(this.searchView.css("max-height",this.option.maxHeight+"px"),t.browser.msie&&this.searchView.css("height",this.searchView.height()>this.option.maxHeight?this.option.maxHeight+"px":"auto")),o.apply(this),this.searchView.css("width",n.apply(this)+"px")}catch(a){return void d.apply(this,[a+""])}this.searchView.show(),h.apply(this,[this.option.listDirection])},r=function(){this.searchView.find("ul").empty(),this.searchView.hide()},h=function(t){var i=this.searchView.find("li.selected");if(i.size())var e="up"===t?i.prev():i.next();else var e="up"===t?this.searchView.find("li").last():this.searchView.find("li").first();if(e.hasClass("disabled"))if(i.size())var e="up"===t?i.prev():i.next();else var e="up"===t?this.searchView.find("li").last():this.searchView.find("li").first();if(e.size()){this.searchView.find("li").removeClass("selected"),e.addClass("selected");var a=e.outerHeight(),s=e.position().top;a+s>this.searchView.height()?this.searchView.scrollTop(this.searchView.scrollTop()+s+a-this.searchView.height()):0>s&&this.searchView.scrollTop(this.searchView.scrollTop()+s)}},l=function(){var i=this,e=this.searchView.find("li.selected");if(e.size()){var a=e.data("data");if(this.inputView.val(a.value),t.isFunction(this.option.afterSelectedHandler))try{this.option.afterSelectedHandler.apply(i,[a])}catch(s){return void d.apply(this,["调用afterSelectedHandler错误:"+s])}r.apply(this)}},c=function(i){jQuery.support.cors=!0;var e=this,a=[],s={async:!1,dataType:e.option.ajaxDataType,type:e.option.ajaxType,timeout:this.option.ajaxTimeout,success:function(i,s,o){if("xml"===e.option.ajaxDataType)t(i).find("item").each(function(){for(var i={value:t(this).text(),label:t(this).text()},e=0;e<this.attributes.length;e++){var s=this.attributes[e].nodeName,o=this.attributes[e].nodeValue;i[s]=o}a.push(i)});else{if("json"!==e.option.ajaxDataType)throw"遇到未知的ajaxDataType参数！";a=i}},error:function(t,i,e){throw e}};if(t.isPlainObject(this.option.ajaxParams))s.data=t.extend(!1,{keyword:i},this.option.ajaxParams);else if(t.isFunction(this.option.ajaxParams))s.data=t.extend(!1,{keyword:i},this.option.ajaxParams.apply(this,[i]));else{if("string"!=typeof this.option.ajaxParams)throw"遇到未知的ajaxParams参数！";s.data="keyword="+i+"&"+this.option.ajaxParams}return t.ajax(this.option.data,s),a},u=function(){var i=this,e=this.inputView.val(),a=[],s=!0,o=[];if(0==t.trim(e).length)return void r.apply(i);if(t.isFunction(this.option.beforeLoadDataHandler))try{s=this.option.beforeLoadDataHandler.apply(this,[e])}catch(n){return void d.apply(this,["调用beforeLoadDataHandler错误:"+n])}if(s)if(t.isArray(this.option.data))a=this.option.data;else if(t.isFunction(this.option.data))try{a=this.option.data.apply(this,[e])}catch(n){return void d.apply(this,["调用data错误:"+n])}else{if("string"!=typeof this.option.data)return void d.apply(this,["遇到未知的data参数！"]);try{a=c.apply(this,[e])}catch(n){return void d.apply(this,["Ajax错误:"+n])}}t.each(a,function(a,s){if(i.option.maxItems>0&&o.length>=i.option.maxItems)return!1;if(t.isPlainObject(s))var n=t.extend(!1,{},s);else{if("string"!=typeof s)return d.apply(i,["数据源Item类型错误！"]),!1;var n={label:s,value:s,image:s}}i.option.matchHandler.apply(i,[e,n])&&o.push(n)}),e==this.inputView.val()&&(o.length>0?p.apply(this,[o]):r.apply(this))},d=function(i){t.isFunction(this.option.onerror)&&this.option.onerror.apply(this,[i])};i.prototype.setOption=function(i){if(t.isPlainObject(i))this.option=t.extend(!1,this.option,i);else{if("string"!=typeof i)return void d.apply(this,["未知的AutoComplete参数类型！"]);switch(i){case"destroy":this.destroy();break;case"show":this.show();break;default:return void d.apply(this,["未知的AutoComplete参数！"])}}},i.prototype.destroy=function(){this.searchView.remove(),this.inputView.unbind("keyup",this._keyup).unbind("keydown",this._keydown).unbind("blur",this._blur),delete this.inputView.get(0).controller},i.prototype.show=function(){this.option.async?setTimeout(function(){u.apply(this)},0):u.apply(this)};var f=function(i,e){var a=RegExp(i.replace(/([.?*+^$[\]\\(){}|-])/g,"\\$1"),"i");return this.option.emphasis&&t.isFunction(this.option.emphasisHandler)&&this.option.emphasisHandler.apply(this,[i,e]),a.test(e.value)},y=function(t,i){var e=RegExp("("+t.replace(/([.?*+^$[\]\\(){}|-])/g,"\\$1")+")","ig");i.label=i.label.replace(e,"<em>$1</em>")}}(jQuery);