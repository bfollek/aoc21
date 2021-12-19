# aoc21

Advent of Code 2021 in clojure.

## Running Tests

```bash
lein test
```

## Days

### Day 1 - Done. 

#### Remember

* `keep_indexed`
* Using `partition` for a sliding window

#### Benchmarking

`count-larger` does a couple of thousand indexed lookups. Running them against a lazy-seq took about *~36ms*. When I converted the lazy-seq to a vector first, the lookups ran in *~2.5ms*. I guess the lazy-seq is a list, with O(n) performance on `nth`.

Also, `nth` was slighly, but consistently, faster than putting the vector in function position: ~2.5ms vs ~2.6ms.

I used `criterium/quick-bench` to benchmark.

### Day 2 - Done.

#### Remember

* `:keys` to destructure a map/record param easily.
* Nice example of `reduce`.
* `throw`ing an exception, and testing for it.

### Day 3 - Done.

#### Remember

* `vector-of` for performance. Unboxed primitives. (Though not a big deal in this case, still good to know.)
* The `as->` threading macro.
* `pr-str` to print a LazySeq.
* `reduced` to return early from `reduce`.
* `format` is like `printf`.

### Day 4 - 

#### Remember

* `to-array-2d`, `aget`, `alength`