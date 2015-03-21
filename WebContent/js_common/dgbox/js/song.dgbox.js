jQuery.fn.dgbox = function(options) {
    options = options || {};
    return this.each(function() {
        var node = this.nodeName.toLowerCase(), self = this;
        if (node == 'a') {
            jQuery(this).click(function() {
                var active = Dgbox.linkedTo(this),
                	href = this.getAttribute('href'),
                    localOptions = jQuery.extend({actuator: this, title: this.title}, options);
                if (active) {
                	return false;
                } else if (href.indexOf('#') >= 0) {
                    var content = jQuery(href.substr(href.indexOf('#'))),
                        newContent = content.clone(true);
                    content.remove();
                    localOptions.unloadOnHide = false;
                    new Dgbox(newContent, localOptions);
                } else { // fall back to AJAX; could do with a same-origin check
                    if (!localOptions.cache) localOptions.unloadOnHide = true;
                    Dgbox.load(this.href, localOptions);
                }
                
                return false;
            });
        } else if (node == 'form') {
            jQuery(this).bind('submit.dgbox', function() {
                Dgbox.confirm(options.message || 'Please confirm:', function() {
                    jQuery(self).unbind('submit.dgbox').submit();
                });
                return false;
            });
        }
    });

};

/**
 * Dgbox Class
 * @param {Object} element
 * @param {Object} options
 */
function Dgbox(element, options) {
	this.visible = false;
	
	this.box = jQuery(Dgbox.WRAPPER);
	jQuery.data(this.box[0], 'dgbox', this);
	this.options = jQuery.extend({}, Dgbox.DEFAULTS, options || {});
	
	// 如果是模态对话框，则不允许拖动
//	if (this.options.modal) {
//		this.options = jQuery.extend(this.options, {
//			draggable : false
//		});
//	}

	// options.actuator == DOM element that opened this dgbox
    // association will be automatically deleted when this dgbox is remove()d
    if (this.options.actuator) {
        jQuery.data(this.options.actuator, 'active.dgbox', this);
    }

	this.setContent(element || '<div></div>');
	this._setupTitleBar();
	
	this.box.css('display', 'none').appendTo(document.body);
	
	if (this.options.fixed) {
        if (jQuery.browser.msie && jQuery.browser.version < 7) {
            this.options.fixed = false; // IE6 doesn't support fixed positioning
        } else {
            this.box.addClass('fixed');
        }
    }

    if (this.options.center && Dgbox._u(this.options.x, this.options.y)) {
        this.center();
    } else {
        this.moveTo(
            Dgbox._u(this.options.x) ? this.options.x : Dgbox.DEFAULT_X,
            Dgbox._u(this.options.y) ? this.options.y : Dgbox.DEFAULT_Y
        );
    }

    this.show();
}

Dgbox.EF = function() {};

jQuery.extend(Dgbox, {
	WRAPPER : "<table cellspacing='0' cellpadding='0' border='0' class='dgbox-wrapper'>"
			+ "<tr><td class='top-left'></td><td class='top'></td><td class='top-right'></td></tr>"
			+ "<tr><td class='left'></td><td class='dgbox-inner'></td><td class='right'></td></tr>"
			+ "<tr><td class='bottom-left'></td><td class='bottom'></td><td class='bottom-right'></td></tr>"
			+ "</table>",

	DEFAULTS : {
		title : null,
		closeable : true,
		draggable : true,
		clone : false,
		actuator : null,
		center : true,
		modal : false,
		fixed : true,
		closeText : '×',
		clickToFront : false,
		behaviours : Dgbox.EF,
		afterDrop : Dgbox.EF,
		afterShow : Dgbox.EF,
		afterClose : Dgbox.EF,
		beforeUnload : Dgbox.EF
	},

	DEFAULT_X : 50,
	DEFAULT_Y : 50,
	zIndex : 1337,
	dragConfigured : false,
	resizeConfigured : false,
	dragging : null,

	load : function(URL, options) {
		options = options || {};

		var ajax = {
			url : URL,
			type : 'GET',
			dataType : 'html',
			cache : false,
			success : function(html) {
				html = jQuery(html);
				if (options.filter)
					html = jQuery(options.filter, html);
				new Dgbox(html, options);
			}
		};

		jQuery.each( [ 'type', 'cache' ], function() {
			if (this in options) {
				ajax[this] = options[this];
				delete options[this];
			}
		});

		jQuery.ajax(ajax);
	},

	_handleResize : function(evt) {
		var d = jQuery(document);
		jQuery('.dgbox-modal-blackout').css('display', 'none')
				.css( {
					width : d.width(),
					height : d.height()
				}).css('display', 'block');
	},
	
	_nextZ: function() {
		return Dgbox.zIndex++;
	},
	
	_u: function() {
        for (var i = 0; i < arguments.length; i++)
            if (typeof arguments[i] != 'undefined') return false;
        
        return true;
    },
    
    _viewport: function() {
        var d = document.documentElement, b = document.body, w = window;
        return jQuery.extend(
            jQuery.browser.msie ?
                { left: b.scrollLeft || d.scrollLeft, top: b.scrollTop || d.scrollTop } :
                { left: w.pageXOffset, top: w.pageYOffset },
            !Dgbox._u(w.innerWidth) ?
                { width: w.innerWidth, height: w.innerHeight } :
                (!Dgbox._u(d) && !Dgbox._u(d.clientWidth) && d.clientWidth != 0 ?
                    { width: d.clientWidth, height: d.clientHeight } :
                    { width: b.clientWidth, height: b.clientHeight }) );
    },
    
    _handleDrag: function(evt) {
        var d;
        if (d = Dgbox.dragging) {
            d[0].box.css({left: evt.pageX - d[1], top: evt.pageY - d[2]});
        }
    },
    
    // displays an alert box with a given message, calling optional callback
    // after dismissal.
    alert: function(message, callback, options) {
        return Dgbox.ask(message, ['确定'], callback, options);
    },
    
    // displays an alert box with a given message, calling after callback iff
    // user selects OK.
    confirm: function(message, after, options) {
        return Dgbox.ask(message, ['确定', '取消'], function(response) {
            if (response == '确定') after();
        }, options);
    },
    
    // asks a question with multiple responses presented as buttons
    // selected item is returned to a callback method.
    // answers may be either an array or a hash. if it's an array, the
    // the callback will received the selected value. if it's a hash,
    // you'll get the corresponding key.
    ask: function(question, answers, callback, options) {
        
        options = jQuery.extend({modal: true, closeable: false},
                                options || {},
                                {show: true, unloadOnHide: true});
        
        var body = jQuery('<div></div>').append(jQuery('<div class="question"></div>').html(question));
        
        // ick
        var map = {}, answerStrings = [];
        if (answers instanceof Array) {
            for (var i = 0; i < answers.length; i++) {
                map[answers[i]] = answers[i];
                answerStrings.push(answers[i]);
            }
        } else {
            for (var k in answers) {
                map[answers[k]] = k;
                answerStrings.push(answers[k]);
            }
        }
        
        var buttons = jQuery('<form class="answers"></form>');
        buttons.html(jQuery.map(answerStrings, function(v) {
            return "<input type='button' value='" + v + "' />";
        }).join(' '));
        
        jQuery('input[type=button]', buttons).click(function() {
            var clicked = this;
            Dgbox.get(this).close(function() {
                if (callback) callback(map[clicked.value]);
            });
        });
        
        body.append(buttons);
        
        new Dgbox(body, options);
        
    },
    
    get: function(ele) {
        var p = jQuery(ele).parents('.dgbox-wrapper');
        return p.length ? jQuery.data(p[0], 'dgbox') : null;
    },
    
    // returns the dgbox instance which has been linked to a given element via the
    // 'actuator' constructor option.
    linkedTo: function(ele) {
        return jQuery.data(ele, 'active.dgbox');
    }
});

Dgbox.prototype = {
	getBox : function() {
		return this.box;
	},

	// Replace dialog content
	setContent : function(newContent) {
		newContent = jQuery(newContent).css( {
			display : 'block'
		}).addClass('dgbox-content');
		if (this.options.clone)
			newContent = newContent.clone(true);
		this.getContent().remove();
		this.getInner().append(newContent);
		this._setupDefaultBehaviours(newContent);
		this.options.behaviours.call(this, newContent);
		return this;
	},

	_setupDefaultBehaviours : function(root) {
		var self = this;
		if (this.options.clickToFront) {
			root.click(function() { self.toTop(); });
		}
		jQuery('.close', root).click(function() {
			self.close();
			return false;
		}).mousedown(function(evt) {
			evt.stopPropagation();
		});
	},

	close : function(after) {
		var self = this;
		if (this.options.modal) {
			jQuery(document.body).unbind('keypress.dgbox');
			this.modalBlackout.animate( {
				opacity : 0
			}, function() {
				jQuery(this).remove();
			});
		}
		this.box.stop().animate( {
			opacity : 0
		}, 300, function() {
			self.box.remove();
			if (after)
				after(self);
			self._fire('afterClose');
			self._fire('beforeUnload');
		});
		if (this.options.actuator) {
			jQuery.data(this.options.actuator, 'active.dgbox', false);
		}
		jQuery.data(this.box[0], 'dgbox', null);
		return this;
	},

	_fire : function(event) {
		this.options[event].call(this);
	},

	show : function() {
		var self = this;
		if (this.options.modal) {
			jQuery(window).resize(function() {
				Dgbox._handleResize();
			});

			this.modalBlackout = jQuery(
					'<div class="dgbox-modal-blackout"></div>').css( {
				zIndex : Dgbox._nextZ(),
				opacity : 0.5,
				width : jQuery(document.body).width(),
				height : jQuery(document.body).height()
			}).appendTo(document.body);
			
			if (this.options.closeable) {
                jQuery(document.body).bind('keypress.dgbox', function(evt) {
                    var key = evt.which || evt.keyCode;
                    if (key == 27) {
                        self.close();
                        jQuery(document.body).unbind('keypress.dgbox');
                    }
                });
            }
		}
		
		this.toTop();
		this.box.stop().css({opacity: 1}).show();
        this._fire('afterShow');
        return this;
	},
	
	estimateSize: function() {
        //this.box.css({visibility: 'hidden', display: 'block'});
        var dims = this.getSize();
        //this.box.css('display', 'none').css('visibility', 'visible');
        return dims;
    },
	
	toTop: function() {
        this.box.css({zIndex: Dgbox._nextZ()});
        return this;
    },
    
    // Returns the dimensions of the entire box dialog as [width,height]
    getSize: function() {
        return [this.box.width(), this.box.height()];
    },
    
    // Returns the dimensions of the content region as [width,height]
    getContentSize: function() {
        var c = this.getContent();
        return [c.width(), c.height()];
    },
    
    // Returns the position of this dialog as [x,y]
    getPosition: function() {
        var b = this.box[0];
        return [b.offsetLeft, b.offsetTop];
    },
    
    // Returns the center point of this dialog as [x,y]
    getCenter: function() {
        var p = this.getPosition();
        var s = this.getSize();
        return [Math.floor(p[0] + s[0] / 2), Math.floor(p[1] + s[1] / 2)];
    },
                
    // Returns a jQuery object wrapping the innerbox region.
    // Not much reason to use this, you're probably more interested in getContent()
    getInner: function() {
		return jQuery('.dgbox-inner', this.box);
    },
    
    // Returns a jQuery object wrapping the box content region.
    // This is the user-editable content area (i.e. excludes titlebar)
    getContent: function() {
        return jQuery('.dgbox-content', this.box);
    },
    
    // Move this dialog to some position, funnily enough
    moveTo: function(x, y) {
        this.moveToX(x).moveToY(y);
        return this;
    },
    
    // Move this dialog (x-coord only)
    moveToX: function(x) {
        if (typeof x == 'number') this.box.css({left: x});
        else this.centerX();
        return this;
    },
    
    // Move this dialog (y-coord only)
    moveToY: function(y) {
        if (typeof y == 'number') this.box.css({top: y});
        else this.centerY();
        return this;
    },
    
    // Move this dialog so that it is centered at (x,y)
    centerAt: function(x, y) {
        var s = this[this.visible ? 'getSize' : 'estimateSize']();
        if (typeof x == 'number') this.moveToX(x - s[0] / 2);
        if (typeof y == 'number') this.moveToY(y - s[1] / 2);
        return this;
    },
    
    centerAtX: function(x) {
        return this.centerAt(x, null);
    },
    
    centerAtY: function(y) {
        return this.centerAt(null, y);
    },
    
    // Center this dialog in the viewport
    // axis is optional, can be 'x', 'y'.
    center: function(axis) {
        var v = Dgbox._viewport();
        var o = this.options.fixed ? [0, 0] : [v.left, v.top];
        if (!axis || axis == 'x') this.centerAt(o[0] + v.width / 2, null);
        if (!axis || axis == 'y') this.centerAt(null, o[1] + v.height / 2);
        return this;
    },
    
    // Center this dialog in the viewport (x-coord only)
    centerX: function() {
        return this.center('x');
    },
    
    // Center this dialog in the viewport (y-coord only)
    centerY: function() {
        return this.center('y');
    },
    
    _setupTitleBar: function() {
        if (this.options.title) {
            var self = this;
            var tb = jQuery("<div class='title-bar'></div>").html("<h2>" + this.options.title + "</h2>");
            if (this.options.closeable) {
                tb.append(jQuery("<a href='#' class='close'></a>").html(this.options.closeText));
            }
            
            if (this.options.draggable) {
                tb[0].onselectstart = function() { return false; }
                tb[0].unselectable = 'on';
                tb[0].style.MozUserSelect = 'none';
                if (!Dgbox.dragConfigured) {
                    jQuery(document).mousemove(Dgbox._handleDrag);
                    Dgbox.dragConfigured = true;
                }
                tb.mousedown(function(evt) {
                    if (self.options.clickToFront) self.toTop();
                    Dgbox.dragging = [self, evt.pageX - self.box[0].offsetLeft, evt.pageY - self.box[0].offsetTop];
                    jQuery(this).addClass('dragging');
                }).mouseup(function() {
                    jQuery(this).removeClass('dragging');
                    Dgbox.dragging = null;
                    self._fire('afterDrop');
                });
            }
            
            this.getInner().prepend(tb);
            this._setupDefaultBehaviours(tb);
        }
    }
};