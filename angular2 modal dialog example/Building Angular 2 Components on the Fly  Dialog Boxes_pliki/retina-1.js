/*!
 * Retina.js v1.3.0-lucid
 *
 * Copyright 2015 Imulus, LLC
 * Released under the MIT license
 *
 * Retina.js is an open source script that makes it easy to serve
 * high-resolution images to devices with retina displays.
 */
(function(){function h(){}function l(a){return f.retinaImageSuffix+a}function e(a,c){this.path=a||"";if("undefined"!==typeof c&&null!==c)this.at_2x_path=c,this.perform_check=!1;else{if(void 0!==document.createElement){var b=document.createElement("a");b.href=this.path;b.pathname=b.pathname.replace(m,l);this.at_2x_path=b.href}else b=this.path.split("?"),b[0]=b[0].replace(m,l),this.at_2x_path=b.join("?");this.perform_check=!0}}function k(a){this.el=a;this.path=new e(this.el.getAttribute("src"),this.el.getAttribute("data-at2x"));
var c=this;this.path.check_2x_variant(function(b){b&&c.swap()})}var d="undefined"===typeof exports?window:exports,f={retinaImageSuffix:"@2x",check_mime_type:!0,force_original_dimensions:!0,check_external:!1,check_method:"HEAD",only_check_marked:!1};d.Retina=h;h.configure=function(a){null===a&&(a={});for(var c in a)a.hasOwnProperty(c)&&(f[c]=a[c])};h.init=function(a){null===a&&(a=d);a.addEventListener("load",function(){var a=document.getElementsByTagName("img"),b=a.length,d=[],e,g;for(e=0;e<b;e+=1)g=
a[e],f.only_check_marked?g.getAttributeNode("data-has-retina")&&g.src&&d.push(new k(g)):g.getAttributeNode("data-no-retina")||g.src&&d.push(new k(g))})};h.isRetina=function(){return 1<d.devicePixelRatio||d.matchMedia&&d.matchMedia("(-webkit-min-device-pixel-ratio: 1.5), (min--moz-device-pixel-ratio: 1.5), (-o-min-device-pixel-ratio: 3/2), (min-resolution: 1.5dppx)").matches?!0:!1};var m=/\.[\w\?=]+$/;d.RetinaImagePath=e;e.confirmed_paths=[];e.prototype.is_external=function(){return!(!this.path.match(/^https?\:/i)||
this.path.match("//"+document.domain))};e.prototype.check_2x_variant=function(a){var c,b=this;if(this.perform_check||"undefined"===typeof this.at_2x_path||null===this.at_2x_path){if(this.at_2x_path in e.confirmed_paths)return a(!0);if(!f.check_external&&this.is_external())return a(!1);c=new XMLHttpRequest;c.open(f.check_method,this.at_2x_path);c.onreadystatechange=function(){if(4===c.readyState&&200<=c.status&&399>=c.status){if(f.check_mime_type){var d=c.getResponseHeader("Content-Type");if(null===
d||!d.match(/^image/i))return a(!1)}e.confirmed_paths.push(b.at_2x_path);return a(!0)}return a(!1)};c.send()}else return a(!0)};d.RetinaImage=k;k.prototype.swap=function(a){function c(){b.el.complete?(f.force_original_dimensions&&(0==b.el.offsetWidth&&0==b.el.offsetHeight?(b.el.setAttribute("width",b.el.naturalWidth),b.el.setAttribute("height",b.el.naturalHeight)):(b.el.setAttribute("width",b.el.offsetWidth),b.el.setAttribute("height",b.el.offsetHeight))),b.el.setAttribute("src",a)):setTimeout(c,
5)}"undefined"===typeof a&&(a=this.path.at_2x_path);var b=this;c()};h.isRetina()&&h.init(d)})();Retina.configure({check_external:!0,check_method:"GET",only_check_marked:!0});