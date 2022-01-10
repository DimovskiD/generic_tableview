# Generic TableView
Generic TableView has been developed to allow users to present data in a versatile ways by using some pre-defined components, or simply extend from them and customize them to their needs.  
  
It all starts with a **GenericListElement**, which should be extended by every class that you want to present in a table. The class takes one mandatory and 3 optional parameters:
- The _columnMap_ is a map of a **GenericView** implementations and a boolean value that indicates if this column should be displayed or not. This parameter is mandatory. The **GenericView** is an abstract class that has to be implemented in order to be instantiated. There are several provided implementations, but you can always create your own custom view and use it.  
- The _type_ is an optional parameter that you can provide, but are not required to. This indicates the 'action type' of the row, or rather the right-most element that is usually used for performing an action for the view. There are several pre-defined RowTypes (ButtonRowType, ChevronRowType, PositiveNegativeRowType and the default - GenericRowType). If you don't supply this parameter, the row won't have any 'action' attached to it. The _actionTextRes_ and _actionIconRes_ are also optional and they are used to set the text of the action and the drawable resource, if applicable.
This is all tied together with the **GenericListAdapter** and its paged alternative **GenericPagedListAdapter**. The adapter takes several parameters for different listeners or UI presentation options. Refer to the documentation for more details.

