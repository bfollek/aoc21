# aoc21

Advent of Code 2021 in clojure.

## Running Tests

```bash
lein test
```

## Days

### Day 1 - Done. 

I learned about:
* `keep_indexed`
* Using `partition` for a sliding window

#### Benchmarking

`count-larger` does a couple of thousand indexed lookups. Running them against a lazy-seq took about *~36ms*. When I converted the lazy-seq to a vector first, the lookups ran in *~2.5ms*. I guess the lazy-seq is a list, with O(n) performance on `nth`.

Also, `nth` was slighly, but consistently, faster than putting the vector in function position: ~2.5ms vs ~2.6ms.

I used `criterium/quick-bench` to benchmark.

### Day 2 - 