### RecyclerView Adapter

[![License](http://img.shields.io/badge/license-APACHE2-blue.svg)](NOTICE)

This library is designed to help separate the creation and binding of views being adapted by a `RecyclerView Adapter`, from the `Adapter` itself.

Instead of writing an `Adapter` that needs to know which layout resource(s) to inflate for the items, as well as binding logic and click listeners, this library allows you to write a `ViewModel` class which wraps your data model, defining how the view should be created, bound, how many spans it should take up, etc.

This makes it easy to use the same `ItemAdapter` class whenever you have a `RecyclerView`.

Creating headers and footers now becomes as simple as creating a `HeaderViewModel` and `FooterViewModel` class, and adding them to you dataset:

``` java
List<ViewModel> items = new ArrayList<>();

items.add(headerViewModel);
items.addAll(myItemViewModels);
items.add(footerViewModel);

viewModelAdapter.setItems(items);
```

The adapter supports diffing between different sets of `ViewModels` (via the `ContentsComparator` interface). Diff results are automatically calculated (on the background thread) and supplied to the `ViewModelAdapter`, which performs the appropriate remove, move & insert `RecyclerView` animations.

`SpanSizeLookup` can be applied to a `GridLayoutManager`, meaning the span size can be defined in the `ViewModel`.

#### Usage

See the sample app for a very basic example.

When you need an adapter, create a new instance of `ViewModelAdapter` and pass it to your `RecyclerView` via `setAdapter`.

Create a `ViewModel`, to wrap the model you're going to display in the `RecyclerView`. You'll also need to create a ViewHolder.

For example, a ViewModel that just wraps a string:

```
// First generic type param is the model, second is the ViewHolder
public class ItemViewModel extends BaseViewModel<String, ItemViewModel.ViewHolder>

    // Our 'model'
    String text;

    // Constructor - force ourselves to pass our model in.
    public ItemViewModel(String text){
        this.text = text;
    }

    // The ViewHolder to be used for our ItemViewModel/
    // This doesn't have to subclass BaseViewHolder, we just get a few perks if it does.
    static class ViewHolder extends BaseViewHolder<ItemViewModel> {

            // View Holder implementation
            TextView textView;
            // ...

    }
```

Now just let implement the required methods using Android Studio's auto-fix feature. (Alt + Enter)

Now it's time to define how the `ViewModel` will present itself to the `Adapter`.

Specify which layout id will be used
```
@Override
public int getLayoutResId() {
    return R.layout.list_item;
}
```

An id that is unique per type of view being adapted. (Usually the layout res id is sufficient)
```
@Override
public int getViewType() {
    return getLayoutResId();
}
```

Create an instance of the `ViewHolder`
```
@Override
public ViewHolder createViewHolder(ViewGroup parent) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(), parent, false));
}
```

Define how the `ViewHolder` should be bound
```
@Override
public void bindView(ViewHolder holder) {
    super.bindView(holder);

    // Bind our model (text) to our holder views.
    holder.textView.setText(text);
}
```

OK, now we've created a `ViewHolder`, defined which layout we're going to use, how the model should be bound to the `ViewHolder` views, and specified a view type, so the `RecyclerView` knows how to recycle our views. We're ready to go.

Back in our controller (`Activity`/`Fragment`/`ViewGroup`/whatever), we just attach a `ViewModelAdapter` to the `RecyclerView`:

```
ViewModelAdapter adapter = new ViewModelAdapter();
recyclerView.setAdapter(adapter);
```

Let's say we have an array of Strings we want to adapt:
```
String[] strings = new String[] {
"Etherum", "Monero", "Zcash", "Dash", "Bitcoin", "LiteCoin", "Golem", "Sia"
}
```

We just need to create a List of `ItemViewModels` to wrap our strings, and pass that to the adapter:

```
List<ViewModel> viewModels = new ArrayList<>();
for (String string: strings){
    viewModels.add(new ViewModel(string));
}

adapter.setItems(viewModels);
```

That's it! You can create as many different types of `ViewModels` as you wish. You no longer need to create `RecyclerView Adapters` to define how the items should be presented.

##### License

Recycler Adapter is released under the Apache 2.0 License , which can be found here: [License](LICENSE.md)