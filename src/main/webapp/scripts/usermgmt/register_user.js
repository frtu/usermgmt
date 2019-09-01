// Self-executing wrapper
(function ($) {
    var Item = Backbone.Model.extend({
        defaults: {
            part1: 'hello',
            part2: 'world'
        }
    });

    var List = Backbone.Collection.extend({
        model: Item
    });

    var ItemView = Backbone.View.extend({
        tagName: 'li', // name of (orphan) root tag in this.el
        initialize: function(){
            _.bindAll(this, 'render'); // every function that uses 'this' as the current object should be in here
        },
        render: function(){
            $(this.el).html('<span>'+this.model.get('part1')+' '+this.model.get('part2')+'</span>');
            return this; // for chainable calls, like .render().el
        }
    });

    // **ListView class**: Our main app view.
    var ListView = Backbone.View.extend({
        el: $('body'), // attaches `this.el` to an existing element.

        // `initialize()`: Automatically called upon instantiation. Where you make all types of bindings, _excluding_ UI events, such as clicks, etc.
        initialize: function () {
            _.bindAll(this, 'render', 'addItem', 'appendItem'); // fixes loss of context for 'this' within methods
            // remember: every function that uses 'this' as the current object should be in here

            this.collection = new List();
            this.collection.bind('add', this.appendItem); // collection event binder

            this.counter = 0; // total number of items added thus far
            this.render(); // not all views are self-rendering. This one is.
        },

        // `render()`: Function in charge of rendering the entire view in `this.el`. Needs to be manually called by the user.
        render: function () {
            $(this.el).append("<button id='add'>Add list item</button>");
            $(this.el).append("<ul></ul>");

            _(this.collection.models).each(function(item){ // in case collection is not empty
                self.appendItem(item);
            }, this);
        },

        events: {
            'click button#add': 'addItem'
        },
        addItem: function(){
            this.counter++;
            var item = new Item();
            item.set({
                part2: item.get('part2') + this.counter // modify item defaults
            });
            this.collection.add(item); // add item to collection; view is updated via event 'add'
        },
        appendItem: function(item){
            $('ul', this.el).append("<li>"+item.get('part1')+" "+item.get('part2')+"</li>");
        }
    });


    // **listView instance**: Instantiate main app view.
    var listView = new ListView();
})(jQuery);

// <div style="float:left; margin-bottom:40px;"><img style="width:36px; margin:5px 10px 0 5px;" src="https://g.twimg.com/Twitter_logo_blue.png"/></div> <div style="background:rgb(245,245,255); padding:10px;">Follow me on Twitter: <a target="_blank" href="http://twitter.com/r2r">@r2r</a> </div>
// <script>
//   if (window.location.href.search(/\?x/) < 0) {
//     var _gaq = _gaq || [];
//     _gaq.push(['_setAccount', 'UA-924459-7']);
//     _gaq.push(['_trackPageview']);
//     (function() {
//       var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
//       ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
//       var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
//     })();
//   }
// </script>
